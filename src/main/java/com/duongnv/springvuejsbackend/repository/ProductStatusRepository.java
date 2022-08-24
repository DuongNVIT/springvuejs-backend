package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.entity.ProductStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatusEntity, Long> {

}


