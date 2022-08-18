package com.duongnv.springvuejsbackend.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Demo {

    @PersistenceContext
    private static EntityManager entityManager;

    public static void main(String[] args) {
//        UserEntity userEntityProxy = entityManager.getReference(UserEntity.class, )
    }
}
