# idea
一些不太常见且实用的场景

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
        user.setName("奇安信");
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