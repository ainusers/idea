package com.qax.idea.dynamicClazz;

import com.qax.idea.dynamicClazz.User;

/**
 * @author: tianyong
 * @time: 2021/9/18 17:29
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
public class UseUser {

    public User invokeUser(){
        User user = new User();
        String name = "cc";
        return user
                .set(name)
                .get()
                .print();
    }

}
