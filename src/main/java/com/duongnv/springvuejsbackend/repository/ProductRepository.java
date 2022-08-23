package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findByCategoryId(Long categoryCode, Pageable pageable);

    Page<ProductEntity> findAll(Pageable pageable);

    @Query("select p from ProductEntity p where p.name like %:productName%")
    Page<ProductEntity> findByName(@Param("productName") String productName, Pageable pageable);

}
