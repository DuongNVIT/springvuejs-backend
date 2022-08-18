package com.duongnv.springvuejsbackend.repository;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class DemoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void doSomething() {
        System.out.println(entityManager);
    }

}
