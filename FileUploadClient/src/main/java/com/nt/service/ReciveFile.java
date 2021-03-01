package com.nt.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ReciveFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReciveFile reciveFile=new ReciveFile();
		reciveFile.downloadFile();
		
			}
	public void downloadFile(){     // This method will download file using RestTemplate
	       try {
	           HttpHeaders headers = new HttpHeaders();
	           headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	           HttpEntity<String> entity = new HttpEntity<>(headers);
	           RestTemplate restTemplate = new RestTemplate();
	           ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/download1", HttpMethod.GET, entity, byte[].class);
	           Files.write(Paths.get("C:\\Upload\\demo1.pdf"), response.getBody());
	           }catch (Exception e){
	           e.printStackTrace();
	       }
	   }


}
