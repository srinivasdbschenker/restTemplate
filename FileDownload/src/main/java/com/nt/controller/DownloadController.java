package com.nt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadController {
	
	private static final String DIRECTORY="c:\\PDF";
	private static final String DEFAULT_FILE_NAME="java-tutorial.pdf";
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/download1")
	public ResponseEntity<InputStreamResource> downloadFile1(@RequestParam(defaultValue=DEFAULT_FILE_NAME)String fileName) throws FileNotFoundException  {
		
		MediaType mediaType=MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
		
		System.out.println("fileName;: "+fileName);
		System.out.println("MediaType: "+mediaType);
		
		File file=new File(DIRECTORY+"/"+fileName);
		
		InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
		
		
		
		
		
		return ResponseEntity.ok()
				//Content-Dispostion
				.header(HttpHeaders.CONTENT_DISPOSITION, "attacchement:fileName="+file.getName())
				//content-type
				.contentType(mediaType)
				.contentLength(file.length())
				.body(resource);
		
	}

}
