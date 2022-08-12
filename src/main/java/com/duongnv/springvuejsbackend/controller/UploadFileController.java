package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.service.impl.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UploadFileController {

    @Autowired
    private AmazonService amazonService;

    @PostMapping("/upload")
    @CrossOrigin("*")
    public String uploadFile(@RequestPart("file")MultipartFile file) {
        System.out.println("Vào upload fiele");
        return amazonService.uploadFile(file);
    }
}
