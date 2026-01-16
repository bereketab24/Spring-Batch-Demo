package com.bereketab.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringBatchApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBatchApplication.class, args);
        System.in.read();
    }

}
