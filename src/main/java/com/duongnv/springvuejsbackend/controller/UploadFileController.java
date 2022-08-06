package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.service.impl.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadFileController {

    @Autowired
    private AmazonService amazonService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file")MultipartFile file) {
        return amazonService.uploadFile(file);
    }
}
