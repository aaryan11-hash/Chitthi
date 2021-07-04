package com.aaryan11hash.chitthi.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlobStorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public String uploadBlobFile(MultipartFile file){

    return "to-do";
    }


}
