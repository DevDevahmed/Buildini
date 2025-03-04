package com.esprit.pifirstversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class PiFirstVersionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiFirstVersionApplication.class, args);
    }

}
