package com.labo.examin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableFeignClients
@SpringBootApplication
public class ExaminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExaminApplication.class, args);
    }

}
