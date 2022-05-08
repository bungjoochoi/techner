package kr.co.biztechpartners.serveSocket.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdDev {

	@Value("${spring.datasource.url}")
    private String datasourceUrl;

	public String getDatasourceUrl() {
		return datasourceUrl;
	}

    
	//운영 서버인지 체크함.
	public boolean isProd() {
		if(getDatasourceUrl().contains("127.0.0.1")) {
			return true;
		}else {
			return false;
		}
	}

}
