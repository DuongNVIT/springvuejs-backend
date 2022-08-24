package com.duongnv.springvuejsbackend.controller.web;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.exception.UnknowException;
import com.duongnv.springvuejsbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories(HttpServletRequest request) {
        try {
            System.out.println("Vào getAllCategories!");
            return categoryService.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Category controller exception!");
            throw new UnknowException("Category controller!");
        }
    }

}
