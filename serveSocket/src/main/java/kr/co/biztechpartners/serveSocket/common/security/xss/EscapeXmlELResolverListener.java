package kr.co.biztechpartners.serveSocket.common.security.xss;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspFactory;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EscapeXmlELResolverListener implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		JspFactory.getDefaultFactory().getJspApplicationContext(servletContext)
				.addELResolver(new EscapeXmlELResolver());
	}

	static class EscapeXmlELResolver extends ELResolver {

		/** pageContext attribute name for flag to enable XML escaping */
		static final String ESCAPE_XML_ATTRIBUTE = EscapeXmlELResolver.class.getName() + ".escapeXml";

		private ThreadLocal<Boolean> excludeMe = new ThreadLocal<Boolean>() {
			@Override
			protected Boolean initialValue() {
				return Boolean.FALSE;
			}
		};

		@Override
		public Class<?> getCommonPropertyType(ELContext context, Object base) {
			return null;
		}

		@Override
		public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
			return null;
		}

		@Override
		public Class<?> getType(ELContext context, Object base, Object property) {
			return null;
		}

		@Override
		public Object getValue(ELContext context, Object base, Object property) {
			JspContext pageContext = (JspContext) context.getContext(JspContext.class);
			Boolean escapeXml = (Boolean) pageContext.getAttribute(ESCAPE_XML_ATTRIBUTE);
			if (escapeXml != null && !escapeXml) {
				return null;
			}

			try {
				if (excludeMe.get()) {
					return null;
				}

				// This resolver is in the original resolver chain. To prevent
				// infinite recursion, set a flag to prevent this resolver from
				// invoking the original resolver chain again when its turn in
				// the
				// chain comes around.
				excludeMe.set(Boolean.TRUE);
				Object value = context.getELResolver().getValue(context, base, property);

				if (value instanceof String) {
					value = EscapeXml.escape((String) value);
				}
				return value;

			} finally {
				excludeMe.remove();
			}
		}

		@Override
		public boolean isReadOnly(ELContext context, Object base, Object property) {
			return true;
		}

		@Override
		public void setValue(ELContext context, Object base, Object property, Object value) {
		}
	}

	static class EscapeXml {

		private static final String[] ESCAPES;

		static {
			int size = '>' + 1; // '>' is the largest escaped value
			ESCAPES = new String[size];
			ESCAPES['<'] = "&lt;";
			ESCAPES['>'] = "&gt;";
			ESCAPES['&'] = "&amp;";
			ESCAPES['\''] = "&#039;";
			ESCAPES['"'] = "&#034;";
		}

		private static String getEscape(char c) {
			if (c < ESCAPES.length) {
				return ESCAPES[c];
			} else {
				return null;
			}
		}

		/**
		 * Escape a string.
		 * 
		 * @param src
		 *            the string to escape; must not be null
		 * @return the escaped string
		 */
		public static String escape(String src) {
			// first pass to determine the length of the buffer so we only
			// allocate once
			int length = 0;
			for (int i = 0; i < src.length(); i++) {
				char c = src.charAt(i);
				String escape = getEscape(c);
				if (escape != null) {
					length += escape.length();
				} else {
					length += 1;
				}
			}

			// skip copy if no escaping is needed
			if (length == src.length()) {
				return src;
			}

			// second pass to build the escaped string
			StringBuilder buf = new StringBuilder(length);
			for (int i = 0; i < src.length(); i++) {
				char c = src.charAt(i);
				String escape = getEscape(c);
				if (escape != null) {
					buf.append(escape);
				} else {
					buf.append(c);
				}
			}
			return buf.toString();
		}
	}
}