package com.duongnv.springvuejsbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
