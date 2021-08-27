package com.qax.idea;

import com.qax.idea.ioc.SpringContext;
import com.qax.idea.ioc.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qax.idea")
public class IdeaApplication {


    private static User user;


    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);

        // ioc模块 entity实体注入测试
        user = SpringContext.getBeanByName("User", User.class);
        user.setAge(12);
        user.setName("奇安信");
        System.out.println(user.toString());

        //


    }

}
