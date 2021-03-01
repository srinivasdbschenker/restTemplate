package com.nt.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpToHttpsConfigV1 {
	
	@Value("${server.http.port:80}")
	private int httpPort;
	
	@Bean
	public ServletWebServerFactory servlerContainer() {
		Connector connector=new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setPort(this.httpPort);
		
		TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(connector);
		
		return tomcat;
		
	}

}
