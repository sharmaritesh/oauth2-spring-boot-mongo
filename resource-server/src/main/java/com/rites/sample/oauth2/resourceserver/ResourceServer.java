package com.rites.sample.oauth2.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rites.sample.oauth2.resourceserver")
public class ResourceServer {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class);
    }
}
