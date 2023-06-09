package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://123.127.93.180:8091/index.html

@SpringBootApplication
public class DemoApplication {

    //http://123.127.93.180:8092
    //    netstat -tunlp | grep 8092
//    nohup java -jar demo-0.0.1-SNAPSHOT.jar >log.out &
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }




}