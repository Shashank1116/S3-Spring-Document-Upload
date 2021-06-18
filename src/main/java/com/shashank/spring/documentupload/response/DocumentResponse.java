package com.shashank.spring.documentupload.response;

public class DocumentResponse {
    private final String fileName;
    private final String link;
    private final String type;
    private final Long size;

    public String getFileName() {
        return fileName;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public Long getSize() {
        return size;
    }

    public DocumentResponse(String fileName, String link, String type, Long size){
        this.fileName = fileName;
        this.size = size;
        this.link = link;
        this.type = type;
    }

}
