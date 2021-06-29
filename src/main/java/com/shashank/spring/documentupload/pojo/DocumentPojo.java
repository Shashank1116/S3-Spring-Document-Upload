package com.shashank.spring.documentupload.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentPojo {
    private DocumentDTO document;
    private String file;
}
