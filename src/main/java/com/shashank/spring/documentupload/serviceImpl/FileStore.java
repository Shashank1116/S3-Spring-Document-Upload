package com.shashank.spring.documentupload.serviceImpl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.shashank.spring.documentupload.config.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@Service
public class FileStore {
    private final AmazonS3 amazonS3;

    @Autowired
    FileStore(AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public void upload(MultipartFile file,String path)  throws IllegalStateException{

        String bucketPath = String.format("%s", BucketName.SHASHANK.getBucketName());


        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type",file.getContentType());
        metaData.put("Content-Length",String.valueOf(file.getSize()));

        ObjectMetadata objectMetadata = new ObjectMetadata();
        metaData.forEach(objectMetadata::addUserMetadata);

        try {
             amazonS3.putObject(bucketPath, path, file.getInputStream(), objectMetadata);
        } catch (AmazonServiceException | IOException exception) {
            throw new IllegalStateException("Failed to upload the file", exception);
        }



    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

}