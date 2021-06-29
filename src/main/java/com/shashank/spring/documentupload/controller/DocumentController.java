package com.shashank.spring.documentupload.controller;


import com.shashank.spring.documentupload.exception.ObjectNotFoundException;
import com.shashank.spring.documentupload.pojo.DocumentPojo;
import com.shashank.spring.documentupload.response.Response;
import com.shashank.spring.documentupload.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @PostMapping("docs")
    ResponseEntity<DocumentPojo> uploadDocument(@RequestBody MultipartFile file,
                                                @RequestParam String documentType) throws ObjectNotFoundException, IOException {
        DocumentPojo response = documentService.uploadDocument(file, documentType);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("docs/{id}")
    ResponseEntity<Response> removeDocument(@PathVariable("id") Long documentId) throws ObjectNotFoundException{
        Response response = documentService.removeDocument(documentId);
        return ResponseEntity.ok().body(response);
    }

}
