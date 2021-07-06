package com.aaryan11hash.chitthi.Services;

import com.aaryan11hash.chitthi.Events.Models.BlobFileMessageEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlobStorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public void uploadBlobFile(BlobFileMessageEvent blobFileMessageEvent){

        File file = convertMultiPartFileToFile(blobFileMessageEvent.getBlob());

        String fileName = String.format("%s|%s",blobFileMessageEvent.getChatId(),blobFileMessageEvent.getTimestamp().toString());
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,file));
        file.delete();
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile){
        File convertedFile = new File(multipartFile.getOriginalFilename());

        try(FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(multipartFile.getBytes());
        }catch (IOException e){
            log.error("Error converting blob file", e);
        }

        return convertedFile;
    }

    public byte[] downloadFile(String fileName){
        S3Object s3Object = s3Client.getObject(bucketName,fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            log.error("Error loading the inputstream data"+ e);
        }
        return null;
    }

    public void deleteFile(String fileName){
        s3Client.deleteObject(bucketName,fileName);

    }

}
