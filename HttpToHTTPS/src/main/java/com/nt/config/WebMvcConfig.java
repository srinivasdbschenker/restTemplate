package com.nt.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
//@Transactional
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private HttpHttpsInterceptor httpHttpsInterceptor;

//
@Override
public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(httpHttpsInterceptor);
registry.addInterceptor(httpHttpsInterceptor)//
.addPathPatterns("/path01", "path02/**");
}        
// Other
	 
}