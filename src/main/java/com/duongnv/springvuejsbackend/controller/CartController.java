package com.duongnv.springvuejsbackend.controller;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.dto.UserDTO;
import com.duongnv.springvuejsbackend.entity.ProductEntity;
import com.duongnv.springvuejsbackend.entity.ProductStatusEntity;
import com.duongnv.springvuejsbackend.entity.UserEntity;
import com.duongnv.springvuejsbackend.entity.UserProductEntity;
import com.duongnv.springvuejsbackend.repository.ProductProjection;
import com.duongnv.springvuejsbackend.repository.ProductRepository;
import com.duongnv.springvuejsbackend.repository.UserProductRepository;
import com.duongnv.springvuejsbackend.repository.UserRepository;
import com.duongnv.springvuejsbackend.security.JwtUtil;
import com.duongnv.springvuejsbackend.service.CartService;
import com.duongnv.springvuejsbackend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @PersistenceContext
    private EntityManager entityManager;

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
    private CartService cartService;

    @GetMapping("/cart")
    public List<ProductProjection> getCart(HttpServletRequest request) {
        String token = jwtUtil.getTokenFromHeader(request);
        String username = jwtUtil.getUsernameFromToken(token);
        UserEntity userEntity = userRepository.findByUsername(username);
        List<ProductProjection> products = userProductRepository.findAllProductInCart(userEntity.getId(), 1l);
        System.out.println(products);
        return products;
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam Long productId, HttpServletRequest request) {
        try {
            System.out.println("Thêm vào giỏ");
            String token = jwtUtil.getTokenFromHeader(request);
            String username = jwtUtil.getUsernameFromToken(token);
            UserEntity user = userRepository.findByUsername(username);
            System.out.println("@@@@@@#######$$$$$$");
            UserProductEntity userProduct = new UserProductEntity();
            userProduct.setUserEntity(entityManager.getReference(UserEntity.class, user.getId()));
            userProduct.setProductEntity(entityManager.getReference(ProductEntity.class, productId));
            userProduct.setProductStatusEntity(entityManager.getReference(ProductStatusEntity.class, 1l));
            userProductRepository.save(userProduct);
            return "Add to cart thành công";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "Add không thành công";
        }
   }

   @PutMapping("/cart")
   public void changeStatusItem(@RequestParam Long userProductId, @RequestParam Long statusId) {

   }

    @DeleteMapping("/cart")
    public void deleteItem(@RequestParam Long userProductId) {
        try {
            System.out.println("Xóa khỏi giỏ");
            System.out.println(userProductId);
            userProductRepository.deleteById(userProductId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
