package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.dto.ProductDTO;
import com.duongnv.springvuejsbackend.entity.UserProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProductEntity, Long> {

    @Query(value = "select p.*, ps.name as status, u.username as username, ps.id as statusId, up.id as userProductId " +
            "from user as u, product as p, user_product as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and u.id = :userId " +
            "and ps.id = :statusId", nativeQuery = true)
    List<ProductProjection> findAllProductInCart(@Param("userId") Long userId, @Param("statusId") Long statusId);

    @Query(value = "select p.*, u.username as username, ps.name as status, ps.id as statusId, up.id as userProductId " +
            "from user as u, product as p, user_product as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and ps.id <> 1", nativeQuery = true)
    List<ProductProjection> findAllProduct();

    @Query(value = "update user_product set statusid = :statusId where id = :userProductId", nativeQuery = true)
    @Modifying
    @Transactional
    void updateStatus(@Param("userProductId") Long userProductId, @Param("statusId") Long statusId);
}
