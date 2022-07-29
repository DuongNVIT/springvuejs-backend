package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.converter.CategoryConverter;
import com.duongnv.springvuejsbackend.converter.ProductConverter;
import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.service.impl.CategoryService;
import com.duongnv.springvuejsbackend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductEntity> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> pageL = null;
        System.out.println(page);
        System.out.println(size);
        pageL = productRepository.findAll(pageable);
        System.out.println(pageL.getTotalElements());
        System.out.println(pageL.getContent());
        System.out.println();
        return pageL.getContent();
    }

    @GetMapping("/products/{category}")
    public List<ProductEntity> getProducts(@PathVariable String category) {
        CategoryDTO categoryDTO = categoryService.findByCategoryCode(category);
        return productService.findByCategoryId(categoryDTO.getId());
    }

    @PostMapping("/products")
    public ProductDTO createProducts(@RequestBody ProductDTO productDTO) {
        System.out.println(58);
        CategoryDTO category = categoryService.findByCategoryCode(productDTO.getCategorycode());
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setNewprice(productDTO.getNewprice());
        product.setOldprice(productDTO.getOleprice());
        product.setCategory(categoryConverter.dtoToEntity(category));
        product.setThumbnail((productDTO.getThumbnail()));
        product = productRepository.save(product);
        return productConverter.entityToDTO(product);
    }

    @GetMapping("/products/search/{name}")
    public List<ProductEntity> searchProduct(@PathVariable(required = false) String name) {
//        if(nam) return productRepository.findAll();
        return productRepository.findByName(name);
    }

    @GetMapping("/products/cart/{userid}")
    public List<ProductEntity> getCart(@PathVariable Long userid) {
        return productRepository.findUserProduct(userid);
    }
}
