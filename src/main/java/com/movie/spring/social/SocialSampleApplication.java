package com.movie.spring.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SocialSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialSampleApplication.class, args);
    }
}
