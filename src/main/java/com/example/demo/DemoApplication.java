package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
//    netstat -tunlp | grep 8092
//    nohup java -jar demo-0.0.1-SNAPSHOT.jar >log.out &
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}