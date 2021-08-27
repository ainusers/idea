package com.qax.idea;

import com.qax.idea.ioc.SpringContext;
import com.qax.idea.ioc.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdeaApplication {


    private static User user;


    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);

        user = SpringContext.getBeanByName("User", User.class);
        System.out.println(user.toString());
    }

}
