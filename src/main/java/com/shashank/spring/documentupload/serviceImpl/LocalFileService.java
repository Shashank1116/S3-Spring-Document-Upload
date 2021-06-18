package com.shashank.spring.documentupload.serviceImpl;

import com.shashank.spring.documentupload.property.FileUploadProperties;
import com.shashank.spring.documentupload.exception.FileStorageException;
import com.shashank.spring.documentupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class LocalFileService implements FileService {

    private final Path filePath;

    @Autowired
    public LocalFileService(FileUploadProperties properties) throws IOException {
        this.filePath = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();
        Files.createDirectories(this.filePath);
    }

    @Override
    public String upload(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }


            Path targetLocation = this.filePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }

    @Override
    public Resource download(String fileName) {
        return null;
    }
}
