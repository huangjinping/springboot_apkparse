package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//http://123.127.93.180:8091/index.html
@EnableScheduling
@SpringBootApplication
public class DemoApplication {


    //sudo -i           管理员登录
    //netstat    -tnlp   查找当前已使用端口
    //http://123.127.93.180:8092
    //    netstat -tunlp | grep 8092
//    nohup java -jar demo-0.0.1-SNAPSHOT.jar >log.out &
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}