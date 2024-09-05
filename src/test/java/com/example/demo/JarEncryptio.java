package com.example.demo;

import io.xjar.XCryptos;

public class JarEncryptio {



//https://mp.weixin.qq.com/s?__biz=MzAxMjY5NDU2Ng==&mid=2651869120&idx=1&sn=64ddb3cc01a507e912c0bce92191e29e&chksm=8106c0770a251e5fde7a86a5cdf8711563b45a0eece5d40a371025f051c7a2db75bdeabd9d5e&scene=27
//nohup ./xjar java --add-opens java.base/jdk.internal.loader=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.net=ALL-UNNAMED -jar demo-0.0.1-encryption.jar
//https://blog.csdn.net/xiao_jiu_xian/article/details/131048012
    public static void main(String[] args) throws Exception {
        System.out.println("success");
        XCryptos.encryption()
                .from("/Users/huhuijie/Documents/GitHub/springboot_apkparse/target/demo-0.0.1-SNAPSHOT.jar")
                .use("wo123456")
                .exclude("/static/**/*")
                .exclude("/templates/**/*")
                .exclude("/META-INF/resources/**/*")
                .to("/Users/huhuijie/Documents/GitHub/springboot_apkparse/target/demo-0.0.1-encryption.jar");
        System.out.println("success");
    }

}
