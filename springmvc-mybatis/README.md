springmvc-mybatis
================================================

# 概述
springmvc-mybatis是一个基于spring boot + spring mvc +  mybatis + mysql + swagger技术的demo项目

# 开发环境
- [jdk1.8][]
- [maven3][]
- [MySQL5+][]
- [lombok][]
- [spring boot][] 
- [spring mvc][]
- [spring validation][] ([validation入门教程][])
- [thymeleaf][] 
- [shiro][]
- [mybatis][]
- [swagger][]
- [logback][]
- [spring aop][]
- [spring boot actuator][]

# 模块说明
- annotation: 自定义注解
- aop: spring aop拦截器
- common: 公共类模块
- config: 项目javaConfig模块
- data：数据访问([mybatis][])类模块
- domain：pojo类模块
- exception: 接口返回的数据模型类模块
- service：业务逻辑类模块
- web：基于[spring mvc][]与[thymeleaf][]模版视图界面模块
    - controller: mvc控制器
    - converter: http message 转换器
    - filter: servlet filter类
    - handler: 项目统一处理器模块(如异常等)
    - model: 视图模型对象类
- resources
    - conf: 项目相关配置文件
    - static: web静态资源文件
    - templates: thymeleaf html模板
- swagger
    - swagger url: http://localhost:8000/swagger-ui.html

# 项目构建
利用maven profile把项目分成三个环境配置项目:dev(开发),test(测试),prod(生产).并能过maven的[filter][]机制在构建时自动替换相关的配置项

## 开发环境构建

```shell
mvn clean package -Dmaven.test.skip=true -Pdev
```
当用IDE（如idea,eclipse)等开发工具进行调试时，需要进行如何下配置才可以正常调试。
- Edit Configuration  
![img-1][]  
- Add Maven Goal   
![img-2][]

## 测试环境构建

```shell
mvn clean package -Dmaven.test.skip=true -Ptest
```

## 生产环境构建

```shell
mvn clean package -Dmaven.test.skip=true -Pprod
```

# 部署 

[jdk1.8]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[maven3]: http://maven.apache.org/download.cgi
[lombok]: https://projectlombok.org/download.html
[tomcat8+]: http://tomcat.apache.org/
[MySQL5+]: http://dev.mysql.com/downloads/mysql/
[spring]: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/
[spring boot]: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
[spring mvc]: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc
[mybatis]: http://www.mybatis.org/mybatis-3/
[swagger]: http://swagger.io/
[logback]: https://logback.qos.ch/manual/
[thymeleaf]: http://www.thymeleaf.org/
[shiro]: http://shiro.apache.org/
[filter]: https://buzheng.org/maven-profile-for-multiple-enviroments.html
[spring validation]: http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/htmlsingle/#validation
[validation入门教程]: http://jinnianshilongnian.iteye.com/blog/1990081
[spring aop]: http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#aop
[spring boot actuator]: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready

[img-1]: https://raw.githubusercontent.com/easytoolsoft/springboot-project-template/master/docs/assets/images/img-1.png
[img-2]: https://raw.githubusercontent.com/easytoolsoft/springboot-project-template/master/docs/assets/images/img-2.png