package com.labo.laboratoire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class LaboratoireApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoireApplication.class, args);
    }

}
