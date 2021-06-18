package com.shashank.spring.documentupload.controller;


import com.amazonaws.services.s3.model.Bucket;
import com.shashank.spring.documentupload.config.BucketName;
import com.shashank.spring.documentupload.response.DocumentResponse;
import com.shashank.spring.documentupload.service.FileService;
import com.shashank.spring.documentupload.serviceImpl.FileStore;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DocumentController {

    private final FileService fileService;
    private final FileStore fileStore;

    @Autowired
    public DocumentController(FileService fileService, FileStore fileStore){
        this.fileService = fileService;
        this.fileStore = fileStore;
    }

    @PostMapping("upload")
    public DocumentResponse upload(@RequestBody MultipartFile file){
        String response = fileService.upload(file);

        String link = ServletUriComponentsBuilder.fromCurrentContextPath().
                                            path("/downloadFile/")
                                            .path(response)
                                            .toUriString();

        return new DocumentResponse(response,link,file.getContentType(),file.getSize());
    }


    @PostMapping(path="upload/s3",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public DocumentResponse uploadToS3(@RequestBody MultipartFile file,@RequestParam String path) throws IllegalStateException{
        fileStore.upload(file,path);
        return new DocumentResponse(file.getOriginalFilename(),path,file.getContentType(),file.getSize());
    }


}
