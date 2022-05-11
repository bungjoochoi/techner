package kr.co.techner.serveSocket.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.techner.serveSocket.common.util.JQGridParameters.Rules;


public class QueryUtil {
	/**
	 * JQGrid Filter를 Query의 조건으로 변환
	 * @param str
	 * @return
	 */
    public static String getQueryCondition(String jsonStr) {
    	
    	String query = "";
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
			//{"groupOp":"AND","rules":[{"field":"classCodeName","op":"eq","data":""}]}
    		//{"groupOp":"AND","rules":[{"field":"plaseMcode","op":"eq","data":"C003"},{"field":"plaseLcode","op":"eq","data":"B002"}],"groups":[]}

    		JQGridParameters data = mapper.readValue(jsonStr, JQGridParameters.class);
    		System.out.println(data.getGroupOp());
    		System.out.println(data.getRules().size());
    		
    		//JdbcUtils
			
    		List<JQGridParameters.Rules> rules = data.getRules();
    		for(int i = 0 ; i < rules.size() ; i++) {
    			System.out.println(rules.get(i).getData());
    			System.out.println(rules.get(i).getField());
    			System.out.println(rules.get(i).getOp());
    			if(rules.get(i).getData() != null && !rules.get(i).getData().equals(""))
    			  query += " AND "+ convertCamelTo(rules.get(i).getField()) + " = '" + rules.get(i).getData() + "'";
    		}
			
			
		} catch (JsonParseException e) {
			query = "";
			e.printStackTrace();
		} catch (JsonMappingException e) {
			query = "";
			e.printStackTrace();
		} catch (IOException e) {
			query = "";
			e.printStackTrace();
		}
    	
		System.out.println(query);

    	return query;
    }
    
    public static String convertCamelTo(String str) {
    	
        Pattern p = Pattern.compile("[A-Z,0-9]");
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "_" + m.group());
        }
        m.appendTail(sb);
        return sb.toString().toUpperCase();
    }
    
}
