package com.example.jjkdle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JjKdleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjKdleApplication.class, args);
    }

}
