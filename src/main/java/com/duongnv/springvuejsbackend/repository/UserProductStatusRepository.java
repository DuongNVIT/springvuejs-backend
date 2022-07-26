package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.dto.ProductProjection;
import com.duongnv.springvuejsbackend.entity.UserProductStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserProductStatusRepository extends JpaRepository<UserProductStatusEntity, Long> {

    @Query(value = "select p.*, ps.name as status, u.username as username, ps.id as statusId, up.id as userProductId " +
            "from user as u, product as p, user_product_status as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and u.id = :userId " +
            "and ps.id = :statusId", nativeQuery = true)
    List<ProductProjection> findAllProductInCart(@Param("userId") Long userId, @Param("statusId") Long statusId);

    @Query(value = "select p.*, u.username as username, ps.name as status, ps.id as statusId, up.id as userProductId " +
            "from user as u, product as p, user_product_status as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and ps.id <> 1", nativeQuery = true)
    List<ProductProjection> findAllProduct();

    @Modifying
    @Transactional
    @Query(value = "update user_product_status set statusid = :statusId where id = :userProductId", nativeQuery = true)
    void updateStatus(@Param("userProductId") Long userProductId, @Param("statusId") Long statusId);


    @Modifying
    @Transactional
    @Query(value = "update user_product_status set statusid = 2 where id in :ids", nativeQuery = true)
    void order(@Param("ids") List<Long> ids);


    @Query(value = "select p.*, ps.name as status, u.username as username, ps.id as statusId, up.id as userProductId " +
            "from user as u, product as p, user_product_status as up, product_status as ps\n" +
            "where up.userid = u.id\n" +
            "and up.productid = p.id\n" +
            "and up.statusid = ps.id\n" +
            "and u.username = :username " +
            "and ps.id <> 1", nativeQuery = true)
    List<ProductProjection> findAllBills(@Param("username") String username);
}
