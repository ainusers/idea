package com.qax.idea.load;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
@Order(value=1)
@Component
class LoadSettings implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("第一个加载");
    }
}


@Order(value=2)
@Component
class LoadSettings2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("第二个加载");
    }
}