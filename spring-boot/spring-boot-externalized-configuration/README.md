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

## JSON属性

环境变量和系统属性会有很多限制，有些名字不能当做环境变量或者系统属性的名字，Spring Boot允许使用JSON格式的属性来解决前面说的限制，使用`spring.application.json`或者`SPRING_APPLICATION_JSON`来指定JSON格式的属性，这些属性被解析后会被添加到`Environment`中，使用示例如下：

- 指定环境变量：`SPRING_APPLICATION_JSON='{"my":{"name":"test"}}'`，启动应用：`java -jar myapp.jar`，`my.name=test`属性会被添加到`Environment`中
- 使用系统属性的方式指定：`java -Dsprnig.application.json='{"my":{"name":"test"}}' -jar myapp.jar`
- 使用命令行参数的方式指定：`java -jar myapp.jar --spring.application.json='{"my":{"name":"test"}}'`
- 使用JNDI的方式指定：`java:comp/env/spring.applicaiton.json`

null值不会覆盖之前的属性

## 外部配置文件

Spring Boot会自动加载`application.properties`文件或者`application.yml`文件，加载的属性会被解析成`PropertySources`并添加到`Environment`中，加载的位置以及顺序如下：

1. 从classpath加载：
   - classpath根目录
   - classpath下的`/config`目录
2. 从当前目录加载：
   - 当前目录
   - 当前目录的子目录：`/config`目录
   - `/config`目录的直接子目录

如果不想使用默认的`application`作为配置文件的名字，可以使用`spring.config.name`来指定想要的配置文件的名字，比如使用如下配置来将配置文件名字改为`myproject`：

```shell
java -jar myproject.jar --spring.config.name=myproject
```

使用上面的方式启动应用后，Spring Boot会加载`myproject.properties`或者`myproject.yml`文件作为配置文件。

还可以使用`spring.config.location`来指定配置文件的位置，多个位置可以使用逗号分割，如果一个指定的位置可能是不存在的，可以使用`optional:`前缀来表示位置可能不存在，这样即使位置不存在，Spring Boot启动的时候也不会报错。如果`spring.config.localtion`中包含目录，需要以`/`结尾，Spring Boot使用的时候会将`spring.config.location`指定的目录和`spring.config.name`指定的文件名直接进行拼接。如果指定了多个位置，将会按照顺序依次进行解析，后面指定的会覆盖掉前面指定的同名属性。

示例如下：

```shell
java -jar myproject.jar --spring.config.location=\
  optional:classpath:/default.properties,\
  optional:classpath:/override.properties
```

由于`spring.config.name`、`spring.config.location`、`spring.config.additioanl-localtion`等属性在Spring Boot启动初期就被需要，所以需要在环境属性中指定这些属性：

- 系统环境变量
- 系统属性
- 命令行参数

### 可选的位置

如果指定了一个不存在的配置文件位置，Spring Boot启动的时候会抛异常`ConfigDataLocationNotFoundException`，可以使用`optional:`前缀来指定一个可能不存在的位置，这样Spring Boot启动的时候即使这个位置不存在也不会抛异常。

如果想要直接忽略`ConfigDataLocationNotFoundException`异常，则可以使用`spring.config.on-not-found=ignore`来进行忽略。

### 通配符

可以在配置文件位置的最后一部分使用`*`，这样可以加载这个目录下的所有配置文件以及直接子目录下的所有配置文件。使用通配符指定位置的时候必须只能包含一个`*`并且必须是以`*/`结尾，匹配到的所有文件以字母顺序排序。通配符只能在外部目录使用，不能在classpath上面使用。

### Profile相关的配置

如果指定了profile名字，则Spring Boot会同时加载`application.properties`和`application-{profile}.properties`文件，带有profile的配置文件会覆盖不带profile的默认的配置文件中的同名属性，如果有多个profile名字指定，则按照顺序后面的名字对应的配置文件会覆盖掉前面的配置文件中的同名属性

### 导入额外的数据

可以在配置文件中使用`spring.config.import`来导入额外的配置文件，使用额外的配置文件导入的属性将会覆盖掉原来配置文件中的同名属性，示例：

```properties
spring.application.name=myapp
spring.config.import=optional:file:./dev/properties
```

### 导入没有扩展名的文件

假设有个myconfig配置文件，没有扩展名，并且想要使用yaml的格式来进行导入，可以使用如下的配置：

```properties
spring.config.import=file:/etc/config/myconfig[.yaml]
```

## 类型安全的配置属性

### JavaBean属性绑定

使用`@ConfigurationProperties("xxxx.yyyy")`配合Java Bean进行属性绑定，示例如下：

```java
@ConfigurationProperties("my.service")
public class MyProperties {

    private boolean enabled;

    private InetAddress remoteAddress;

    private final Security security = new Security();

    // getters / setters...

    public static class Security {

        private String username;

        private String password;

        private List<String> roles = new ArrayList<>(Collections.singleton("USER"));

        // getters / setters...

    }

}
```

这种方式只支持普通的Java Bean属性，不支持静态属性。

### 构造器绑定

使用`@EnableConfigurationProperties`或者使用配置扫描`@ConfigurationPropertiesScan`来启用构造器绑定，使用`@ConfigurationProperties`和`@ConstructorBinding`配合Java Bean进行构造器绑定，示例如下：

```java
@ConstructorBinding
@ConfigurationProperties("my.service")
public class MyProperties {

    // fields...

    public MyProperties(boolean enabled, InetAddress remoteAddress, Security security) {
        this.enabled = enabled;
        this.remoteAddress = remoteAddress;
        this.security = security;
    }

    // getters...

    public static class Security {

        // fields...

        public Security(String username, String password, @DefaultValue("USER") List<String> roles) {
            this.username = username;
            this.password = password;
            this.roles = roles;
        }

        // getters...

    }

}
```

# 示例代码

- 环境变量：`APP_NAME="app name from environment";SPRING_APPLICATION_JSON='{"app":{"name":"app name from environment json"}}'`
- 命令行参数：`--app.name="app name from command line arguments" --spring.application.json='{"app":{"name":"app name from command line json"}}'`
- java系统变量：`-Dapp.name="app name from java vm options" -Dspring.application.json='{"app":{"name":"app name from java vm options json"}}'`

打包：`mvn clean package`

使用如下命令运行：

```shell
APP_NAME="app name from environment";SPRING_APPLICATION_JSON='{"app":{"name":"app name from environment json"}}' java -Dapp.name="app name from java vm options" -Dspring.application.json='{"app":{"name":"app name from java vm options json"}}' -jar target/spring-boot-externalized-configuration-1.0.0-SNAPSHOT.jar --app.name="app name from command line arguments" --spring.application.json='{"app":{"name":"app name from command line json"}}'
```