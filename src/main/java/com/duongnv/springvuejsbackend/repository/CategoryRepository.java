package com.duongnv.springvuejsbackend.repository;

import com.duongnv.springvuejsbackend.dto.CategoryDTO;
import com.duongnv.springvuejsbackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCode(String code);
}
