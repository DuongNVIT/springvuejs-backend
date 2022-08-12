package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.UserProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProductEntity, Long> {

    @Query(value = "select p.*, ps.name as status from user as u, product as p, user_product as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and u.id = :userId " +
            "and ps.id = :statusId", nativeQuery = true)
    List<ProductProjection> findAllProductInCart(@Param("userId") Long userId, @Param("statusId") Long statusId);
}
