package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import com.duongnv.springvuejsbackend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        System.out.println("Vào getAllCategories!");
        return categoryService.findAll();
    }

}
