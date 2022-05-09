package kr.co.biztechpartners.serveSocket.common.viewdecoration.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter{

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// TODO Auto-generated method stub
		builder.addDecoratorPath("/main*", "/WEB-INF/decorator/maindeco.jsp")
			   .addDecoratorPath("/overview*", "/WEB-INF/decorator/maindeco.jsp")
			   .addDecoratorPath("**/list*", "/WEB-INF/decorator/listdeco.jsp")
			   .addDecoratorPath("**List*", "/WEB-INF/decorator/listdeco.jsp")
			   .addDecoratorPath("**Create*", "/WEB-INF/decorator/editdeco.jsp")
			   .addDecoratorPath("**Info", "/WEB-INF/decorator/editdeco.jsp")
			   .addDecoratorPath("**/modify*", "/WEB-INF/decorator/editdeco.jsp")
			   .addDecoratorPath("**/view*", "/WEB-INF/decorator/editdeco.jsp")
			   .addDecoratorPath("**/admin*", "/WEB-INF/decorator/editdeco.jsp")
//			.addExcludedPath("/common/*");
		       .addExcludedPath("/login/*")
		       .addExcludedPath("/user/listSearchCompany")
		       .addExcludedPath("/companyCreate")
		       .addExcludedPath("/company/modify/*")
		       .addExcludedPath("/company/create");
		
		/*
		 *   /업무/행위/
		 *   
		 *   공지사항 -> notice  , 목록 -> list
		 *   /notice/list
		 *   /notice/view
		 *   
		 *   /csr/allList
		 *   /csr/myList
		 *    
		 * 
		 */
	}
}
