package com.dangqp.moduleone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ModuleOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleOneApplication.class, args);
        ExecutorService pool = Executors.newFixedThreadPool(1);
    }

}
