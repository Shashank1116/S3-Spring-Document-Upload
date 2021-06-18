package com.shashank.spring.documentupload.config;


public enum BucketName {
    SHASHANK("shashankgbucket");
    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}