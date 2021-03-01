package com.nt.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HttpToHttpsConfigV2 {
	
	//ift this parameter is empthy then do not redirect Http to HTTPs
	
	//defined in application.properties
	
	@Value(value="${server.ssl.key-store}")
	private String sslKeyStore;
	
	//defined in application.proties file
	@Value(value="@{server.http.port:80}")
	private int httpPort;
	
	@Value(value="@{server.port:443")
	int httpsPort;
	
	
	@Bean
	public ServletWebServerFactory servletContainer() {
		
		boolean needRedirectToHtps=sslKeyStore !=null && !sslKeyStore.isEmpty();
		
		TomcatServletWebServerFactory tomcat=null;
		
		if(!needRedirectToHtps) {
			tomcat=new TomcatServletWebServerFactory();
			return tomcat;
			
		}
		
		tomcat=new TomcatServletWebServerFactory() {
			
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint=new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				
			SecurityCollection collection=new SecurityCollection();
			collection.addPattern("/*");
			securityConstraint.addCollection(collection);
			context.addConstraint(securityConstraint);
			
				
			}
		};
		
		tomcat.addAdditionalTomcatConnectors(redirectConnectors());
		
		return tomcat;
		
	}


	private Connector redirectConnectors() {
		// TODO Auto-generated method stub
		
		Connector connector=new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
         connector.setPort(httpPort);
         connector.setSecure(false);
         connector.setRedirectPort(httpsPort);
		return connector;
	}
	
	
	
	

}
