package com.shashank.spring.documentupload.service;

import com.shashank.spring.documentupload.exception.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
     String upload(MultipartFile file,String path,String uniqueIdentifier);
     byte[] download(String path,String key);
     void delete(String path) throws ObjectNotFoundException;
}
