package com.shashank.spring.documentupload.serviceImpl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import com.shashank.spring.documentupload.config.BucketName;
import com.shashank.spring.documentupload.exception.ObjectNotFoundException;
import com.shashank.spring.documentupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AmazonS3ServiceImpl implements FileService {
    private final AmazonS3 amazonS3;




    @Autowired
    AmazonS3ServiceImpl(AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;

    }

    public String upload(MultipartFile file,String path,String uniqueIdentifier)  throws IllegalStateException{

        String bucketPath = String.format("%s", BucketName.SHASHANK.getBucketName());

        String fileName = file.getOriginalFilename();
        String fileUrl =  path+"/"+ uniqueIdentifier+"_"+fileName;
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type",file.getContentType());
        metaData.put("Content-Length",String.valueOf(file.getSize()));

        ObjectMetadata objectMetadata = new ObjectMetadata();
        metaData.forEach(objectMetadata::addUserMetadata);

        try {
             amazonS3.putObject(bucketPath, fileUrl, file.getInputStream(), objectMetadata);
        } catch (AmazonServiceException | IOException exception) {
            throw new IllegalStateException("Failed to upload the file", exception);
        }

        return fileUrl;

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

    @Override
    public void delete(String path) throws ObjectNotFoundException {
        try {
            final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(BucketName.SHASHANK.getBucketName(), path);
            amazonS3.deleteObject(deleteObjectRequest);

        }catch (AmazonServiceException exception){
            throw new ObjectNotFoundException("Resource not found", HttpStatus.NOT_FOUND);
        }
    }


}