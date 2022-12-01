package com.kartamonov.imager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.kartamonov.data.model")
public class ImagerApplication {
    public static void main(String[] args){
        SpringApplication.run(ImagerApplication.class, args);
    }
}
