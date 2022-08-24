package com.duongnv.springvuejsbackend.controller.admin;

import com.duongnv.springvuejsbackend.exception.UploadFileException;
import com.duongnv.springvuejsbackend.service.UploadFileService;
import com.duongnv.springvuejsbackend.service.impl.UploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@Transactional
public class UploadFileController {

    @Autowired
    private UploadFileService amazonService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Vào upload fiele");
            return amazonService.uploadFile(file);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UploadFileException("Upload file error!");
        }
    }
}
