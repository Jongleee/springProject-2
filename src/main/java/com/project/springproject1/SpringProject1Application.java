package com.project.springproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringProject1Application.class, args);
    }

}
