package com.qax.idea.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author: tianyong
 * @time: 2021/8/30 18:36
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext arg0) {
        applicationContext = arg0;
    }

    // 取applicationContext对象
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 根据bean的id来查找对象
    public static <T> T getBeanByName(String name, Class<T> tClass) {
        return tClass.cast(applicationContext.getBean(name));
    }

    // 根据bean的class来查找对象
    public static <T> T getBeanByClass(Class<T> c) {
        return applicationContext.getBean(c);
    }

    // 根据bean的class来查找所有的对象(包括子类)
    public static <T> Map<String, T> getBeansByClass(Class<T> c) {
        return applicationContext.getBeansOfType(c);
    }

    // test-method
    public static void info() {
        System.out.println(String.format("SpringContext %s 程序启动！", applicationContext.getApplicationName()));
    }
}

