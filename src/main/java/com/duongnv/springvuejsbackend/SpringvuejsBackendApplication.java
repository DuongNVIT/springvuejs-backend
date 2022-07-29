package com.duongnv.springvuejsbackend;

import com.duongnv.springvuejsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringvuejsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringvuejsBackendApplication.class, args);
	}

}
