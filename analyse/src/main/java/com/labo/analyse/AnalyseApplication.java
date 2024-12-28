package com.labo.analyse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AnalyseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyseApplication.class, args);
    }

}