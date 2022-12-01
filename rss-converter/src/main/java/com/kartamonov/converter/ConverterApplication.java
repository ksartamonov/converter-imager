package com.kartamonov.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.kartamonov.data.model")
public class ConverterApplication {
    public static void main(String[] args){
        SpringApplication.run(ConverterApplication.class, args);
    }
}