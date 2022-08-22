package com.duongnv.springvuejsbackend.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.duongnv.springvuejsbackend.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService {

    private AmazonS3 amazonS3;

    @Value("${amazonProperties.endpointUrl}")
    private String endPointURL;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    public void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-southeast-1")
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        System.out.println(endPointURL);
        System.out.println(accessKey);
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File sendFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(sendFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return sendFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        System.out.println(amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead)));
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String fileUrl = "";
        File file = null;
        file = convertMultipartFileToFile(multipartFile);
        String fileName = generateFileName(multipartFile);
        fileUrl = endPointURL + "/" + bucketName + "/" + fileName;
        uploadFileTos3bucket(fileName, file);
        return fileUrl;
    }
}
