package com.qax.idea;

import com.qax.idea.ioc.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qax.idea")
public class IdeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);
    }
}
