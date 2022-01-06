package com.qax.idea.dynamicClazz;

/**
 * @author: tianyong
 * @time: 2021/9/18 17:18
 * @description:
 * @Version: v1.0
 * @company: QiXin Group.Situation xx事业部
 */
public class User {


    public User print(){
        System.out.println("print");
        return this;
    }

    public User set(String name){
        System.out.println(name);
        return this;
    }

    public User get(){
        System.out.println("get");
        return this;
    }


}
