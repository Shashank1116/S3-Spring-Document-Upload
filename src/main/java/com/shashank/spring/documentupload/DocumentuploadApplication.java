package com.shashank.spring.documentupload;

import com.shashank.spring.documentupload.property.FileUploadProperties;
import com.shashank.spring.documentupload.serviceImpl.LocalFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
public class DocumentuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentuploadApplication.class, args);
	}

}
