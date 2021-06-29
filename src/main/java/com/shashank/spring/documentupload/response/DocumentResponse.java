package com.shashank.spring.documentupload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;

@Data
@AllArgsConstructor
public class DocumentResponse {
    private final String fileName;
    private final String link;
    private final String type;
    private final Long size;
    private final ByteArrayResource file;
}
