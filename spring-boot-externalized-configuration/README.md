# Spring Boor Externalized Configuration

Spring Boot允许将配置外部化，这样可以让同一份应用代码使用在不同的的环境中，外部化配置方式有很多种，包括：Java Properties文件、YAML文件、环境变量、命令行参数等。Spring Boot允许同时使用多种不同的配置方式，但是这些配置一起使用时会有先后顺序，如果一个属性出现在了多个配置里，按照Spring Boot规定的顺序排在后面的会覆盖掉前面的配置中的同名属性的值。Spring Boot规定的配置的加载顺序如下：

1. 默认属性，通过`SpringApplication.setDefaultProperties`进行设置的属性
2. 在同时有`@Configuration`注解和`@PropertySource`注解的类加载的属性。此类属性需要等到应用上下文被刷新之后才会添加到`Environment`中，所以对于`logging.*`以及`spring.main.*`这样的需要在应用上下文刷新之前就需要的属性，使用此种方式配置就不太合适
3. 配置数据，比如：`application.properties`文件
4. 使用`RandomValuePropertySource`给`random.*`属性生成随机数
5. 操作系统环境变量
6. Java系统属性(`System.getProperties()`)
7. 使用`java:comp/env`获取的JNDI属性
8. `ServletContext`的`init`参数
9. `ServletConfig`的`init`参数
10. `SPRING_APPLICATION_JSON`指定的JSON格式的属性（环境变量或者系统属性）
11. 命令行参数
12. 测试类上面的`properties`属性，比如：`@SpringBootTest`
13. 测试类上的`@TestPropertySource`注解引入的配置
14. 如果`devtool`启用，可以从`$HOME/.conf/spring-boot`目录下加载全局设置属性

其中上面第三条中的配置数据也有多种形式，遵循以下顺序：

1. 在应用jar包内的`application.properties`或者`application.yml`文件
2. 在应用jar包内的和Profile相关的文件: `application-{profile}.properties`或者`application-{profile}.yml`文件
3. 在应用jar包外的`application.properties`或者`application.yml`文件
4. 在应用jar包外的和Profile相关的文件: `application-{profile}.properties`或者`application-{profile}.yml`文件

另外如果应用中同时存在`yml`文件和`properties`文件，则`properties`文件会生效。

## 命令行属性

默认情况下Spring Boot会将命令行参数中的属性（以`--`开头的参数，比如`--server.prot=8080`）添加到Spring的`Environment`中，如果不想添加到`Environment`中可以使用`SpringApplication.setAddCommandLineProperties(false)`来禁用。命令行参数中的属性比基于文件的属性有更高的优先级。