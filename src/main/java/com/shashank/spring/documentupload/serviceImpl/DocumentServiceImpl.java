package com.shashank.spring.documentupload.serviceImpl;


import com.shashank.spring.documentupload.constants.RestMappingConstants;
import com.shashank.spring.documentupload.entity.Document;
import com.shashank.spring.documentupload.exception.ObjectNotFoundException;
import com.shashank.spring.documentupload.pojo.DocumentPojo;
import com.shashank.spring.documentupload.repo.DocumentRepository;
import com.shashank.spring.documentupload.response.Response;
import com.shashank.spring.documentupload.service.DocumentService;
import com.shashank.spring.documentupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileService fileService;


    @Autowired
    DocumentServiceImpl(DocumentRepository documentRepository, FileService fileService){

        this.documentRepository = documentRepository;

        this.fileService = fileService;
    }


    @Override
    public DocumentPojo uploadDocument(MultipartFile file, String category) throws IOException {

        DocumentPojo response  = new DocumentPojo();
        Document newDocument = new Document();
        newDocument.setCategoryName(category);
        Document document = documentRepository.save(newDocument);


        String path = "MY_FILE_PATH";
        String resourceUrl  = fileService.upload(file,path,document.getId().toString());

        document.setResourceURL(resourceUrl);
        documentRepository.save(document);
        response.setDocument(document.toDTO());
        response.setFile(DatatypeConverter.printBase64Binary(file.getBytes()));
        return response;
    }

    @Override
    public Response removeDocument(Long id) throws ObjectNotFoundException{
        Document document = documentRepository.findById(id).orElseThrow(
                ()->new ObjectNotFoundException("No document found for id:"+id,HttpStatus.NOT_FOUND));
        documentRepository.delete(document);
        fileService.delete(document.getResourceURL());
        return new Response(RestMappingConstants.SUCCESS,"Document removed successfully", HttpStatus.OK);
    }



}
