package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.exception.UploadFileException;
import com.duongnv.springvuejsbackend.service.impl.UploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UploadFileController {

    @Autowired
    private UploadFileServiceImpl amazonService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Vào upload fiele");
            return amazonService.uploadFile(file);
        } catch (Exception exception) {
            throw new UploadFileException("Upload file error!");
        }
    }
}
