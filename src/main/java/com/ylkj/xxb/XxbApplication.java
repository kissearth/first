package com.ylkj.xxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ylkj.xxb"})
public class XxbApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxbApplication.class, args);
    }
}
