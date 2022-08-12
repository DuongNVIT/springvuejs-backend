package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.entity.ProductStatusEntity;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.entity.UserProductEntity;
import com.duongnv.springvuejsbackend.repository.*;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.CartService;
import com.duongnv.springvuejsbackend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CartController {

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @GetMapping("/cart/{statusid}")
    public List<ProductProjection> getCart(HttpServletRequest request, @PathVariable(value = "statusid", required = false   ) Long statusId) {
        String token = jwtUtil.getTokenFromHeader(request);
        String username = jwtUtil.getUsernameFromToken(token);
        UserEntity userEntity = userRepository.findByUsername(username);
        List<ProductProjection> products = userProductRepository.findAllProductInCart(userEntity.getId(), statusId);
//        List<ProductDTO> products = userProductRepository.findAllProductInCart(userEntity.getId(), statusId);
        System.out.println(products);
        return products;
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam Long productId, HttpServletRequest request) {
        String token = jwtUtil.getTokenFromHeader(request);
        String username = jwtUtil.getUsernameFromToken(token);
        UserEntity user = userRepository.findByUsername(username);
        ProductEntity product = productRepository.findById(Long.parseLong(productId.toString()));
        UserProductEntity userProduct = new UserProductEntity();
//        ProductStatusEntity productStatus = productStatusRepository.findById(1l);
//        userProduct.setProductStatus(productStatus);
        userProduct.setUserId(user.getId());
        userProduct.setProductId(productId);
        userProductRepository.save(userProduct);
        return "Add to cart thành công";
   }
}
