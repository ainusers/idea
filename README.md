# idea
一些不太常见且实用的场景


### 1. IOC模块 (将entity交给spring管理) </br>
常规场景和看法
因为DAO层一般无状态。而实体bean要保持状态信息，或者说不同请求内容可能是不一样的，所以要用不同的实例。
这个是要综合考虑的问题。就拿我们在工作中的很常见的例子来说：我们会将controller、service、dao中的class交由spring管理并注入，
是因为一般情况下在整个程序运行周期内，这些class只会被实例化一次，这恰好能和spring中的singleton scope相吻合。
但是我们几乎很少将entity中的class交由spring管理，因为我们无法确定这些class对应的bean的生命周期。
所以其实归结一句话：考虑是否将一个class交由spring管理，关键看这个class产生的bean是否符合spring提供的scope的生命周期规则
