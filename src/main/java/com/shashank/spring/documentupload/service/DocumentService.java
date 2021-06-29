package com.shashank.spring.documentupload.service;


import com.shashank.spring.documentupload.exception.ObjectNotFoundException;
import com.shashank.spring.documentupload.pojo.DocumentDTO;
import com.shashank.spring.documentupload.pojo.DocumentPojo;
import com.shashank.spring.documentupload.response.Response;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    DocumentPojo uploadDocument(MultipartFile file, String category) throws IOException;
    Response removeDocument(Long id) throws ObjectNotFoundException;
}
