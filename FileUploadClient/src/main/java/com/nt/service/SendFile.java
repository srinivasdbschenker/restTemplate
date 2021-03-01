package com.nt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class SendFile {

	private static final Logger log = LoggerFactory.getLogger(SendFile.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static void main(String[] args) throws FileNotFoundException {
		SendFile s=new SendFile();
		s.sendXmlFile();
	}


	

	   
	public void sendXmlFile() throws FileNotFoundException {
		
		   log.info("The time is now {}", dateFormat.format(new Date()));
		File folder = new File("C:\\ALDIandACFS\\source");
		
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			System.out.println("file:: " + file);
			if (file.isFile() ) {
				// Scanner
				System.out.println("file::  " + file.getName());

				String filename = "C:\\ALDIandACFS\\source\\" + file.getName();
				
				MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		
				body.add("file",filename);
				


				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
				HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);
				RestTemplate restTemplate = new RestTemplate();
				
				ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080//uploadMultiFile",HttpMethod.GET,requestEntity,String.class);
				
				
				// Create a list for the message converters
				List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
				// Add the String Message converter
				messageConverters.add(new StringHttpMessageConverter());
				// Add the message converters to the restTemplate
				restTemplate.setMessageConverters(messageConverters);
				
			
			}
		}
	}
}
