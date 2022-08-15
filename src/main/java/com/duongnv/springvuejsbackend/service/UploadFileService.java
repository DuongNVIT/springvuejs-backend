package com.duongnv.springvuejsbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String uploadFile(MultipartFile multipartFile);
}
