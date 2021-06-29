package com.shashank.spring.documentupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.shashank.spring.documentupload.property.FileUploadProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {


    @Value("${aws.access-key}")
    private String accessKey;

    @Value("${aws.secret}")
    private String secret;

    @Value("${aws.region}")

    @Bean
    public AmazonS3 s3() {
        AWSCredentials
                awsCredentials =
                new BasicAWSCredentials(accessKey, secret);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}