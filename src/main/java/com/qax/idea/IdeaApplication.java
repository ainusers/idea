package com.qax.idea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qax.idea")
public class IdeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);
    }
}
