package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByCategoryId(Long categoryCode);

    @Query("select p from ProductEntity p where p.name like %?1%")
    List<ProductEntity> findByName(String id);

    @Query(value = "select p.* from product as p, user_product as up " +
            "where p.id = up.productid and up.userid = ?1" , nativeQuery = true)
    List<ProductEntity> findUserProduct(Long userid);
}
