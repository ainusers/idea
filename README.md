#### 1. IOC模块 (将entity交给spring管理)
##### 1.1. 常规场景和看法
因为DAO层一般无状态。而实体bean要保持状态信息，或者说不同请求内容可能是不一样的，所以要用不同的实例。
这个是要综合考虑的问题。就拿我们在工作中的很常见的例子来说：我们会将controller、service、dao中的class交由spring管理并注入，
是因为一般情况下在整个程序运行周期内，这些class只会被实例化一次，这恰好能和spring中的singleton scope相吻合。
但是我们几乎很少将entity中的class交由spring管理，因为我们无法确定这些class对应的bean的生命周期。
所以其实归结一句话：考虑是否将一个class交由spring管理，关键看这个class产生的bean是否符合spring提供的scope的生命周期规则

##### 1.2. 测试代码
@SpringBootApplication(scanBasePackages = "com.qax.idea")
public class IdeaApplication {
    private static User user;
    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);
        user = SpringContext.getBeanByName("User", User.class);
        user.setAge(12);
        user.setName("奇信");
        System.out.println(user.toString());
    }
}


#### 2. LOAD模块 (在springboot项目启动之前提前加载数据或配置)
##### 2.1. 测试代码
这部分不需要代码测试，只需要启动IdeaAoolication启动类即可


#### 3. FACTORY模块 (工厂方法创建对象)
##### 3.1. 测试代码
ConfigureFactory configureFactory = new ConfigureFactoryImpl();
YmlConfigure configure = configureFactory.factory(YmlConfigure.class, "");
System.out.println(configure.get());


#### 4. SPI模块 (ServiceLoader.load:动态配置操作实现类)
##### 4.1. 测试代码
这部分不需要代码测试，只需要启动IdeaAoolication启动类即可


#### 5. CHAININVOKE模块 (构造链式调用模块)
##### 5.1. 测试代码
@Test
void contextLoads() throws Exception {
    new BaseStream()
      .getInstance()
      .dataHandle()
      .print();
}

#### 6. CHECK模块 (前缀树实现敏感词检测)


#### 7. 动态类
@Test
void contextLoads() throws Exception {
    String name = "cc";
    String model = "set(name).get().print()";
    //groovy提供了一种将字符串文本代码直接转换成Java Class对象的功能
    GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
    //里面的文本是Java代码,但是我们可以看到这是一个字符串我们可以直接生成对应的Class<?>对象,而不需要我们写一个.java文件
    Class<?> clazz = groovyClassLoader.parseClass("" +
            "package com.qax.idea;\n" +
            "\n" +
            "/**\n" +
            " * @author: tianyong\n" +
            " * @time: 2021/9/18 17:29\n" +
            " * @description:\n" +
            " * @Version: v1.0\n" +
            " * @company: QiXin Group.Situation xx事业部\n" +
            " */\n" +
            "public class CC {\n" +
            "\n" +
            "    public User invokeUser(String name){\n" +
            "        User user = new User();\n" +
            "        System.out.println(name);\n" +
            "        return user."+model+";\n" +
            "    }\n" +
            "\n" +
            "}\n");
    Object obj = clazz.newInstance();
    Method method = clazz.getDeclaredMethod("invokeUser",String.class);
    method.invoke(obj,name);

    /*Object val = method.getDefaultValue();
    System.out.println(val);*/
}


#### 8. MATCHING模块 (规则检测）


#### 9. debug动态调试模块 (算子编排）
