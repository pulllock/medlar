# spring-test

- `@BootstrapWith`：用来配置Spring测试上下文的启动类
- `@ContextConfiguration`：用来指定加载和配置ApplicationContext的配置
- `@WebAppConfiguration`：用来声明加载的ApplicationContext是一个WebApplicationContext
- `@ContextHierarchy`：用来指定一些具有继承关系的`@ContextConfiguration`配置
- `@ActiveProfiles`：用来指定激活的Profile
- `@TestPropertySource`：用来指定properties文件的位置或者是直接声明key-value属性
- `@DynamicPropertySource`：是一个方法级别的注解，可以用来注册动态的属性到PropertySources中
- `@DirtiesContext`：用来指明当前ApplicationContext在执行的过程中被污染了，执行结束后会将ApplicationContext移除并关闭
- `@TestExecutionListeners`：用来指定TestExecutionListener的实现，并注册到TestContextManager中，需要和`@ContextConfiguration`配合使用
- `@RecordApplicationEvents`：让Spring TestContext记录所有的应用事件
- `@Commit`：在方法执行完成后提交事务
- `@Rollback`：在方法执行完成后回滚事务
- `@BeforeTransaction`：在事务执行前执行
- `@AfterTransaction`：在事务执行后执行
- `@Sql`：指定执行的SQL脚本
- `@SqlConfig`：定义一些元数据，用来决定怎样去解析和运行在`@Sql`中指定的SQL脚本
- `@SqlMergeMode`：用来配置类级别的`@Sql`和方法级别的`@Sql`怎样合并
- `@SqlGroup`：可以将多个`Sql`放到一个组里

## junit4

- `@RunWith(SpringJUnit4ClassRunner.class)`
- `@RunWith(SpringRunner.class)`：是`@RunWith(SpringJUnit4ClassRunner.class)`的缩写版本
- `AbstractJUnit4SpringContextTests`
- `AbstractTransactionalJUnit4SpringContextTests`

## junit5

- `@ExtendWith(SpringExtension.class)`

## WebTestClient

`WebTestClient`是一个基于`WebClient`的，用来测试web服务的HTTP客户端，配置的方式有如下：

- 绑定一个`Controller`，对请求和响应进行mock，不需要启动web容器，`Spring MVC`中使用：`WebTestClient client = MockMvcWebTestClient.bindToController(new TestController()).build();`
- 绑定`ApplicationContext`，可以加载一个`Spring MVC`的上下文，对请求和响应进行mock，不需要启动web容器，`Spring MVC`中使用：`WebTestClient client = MockMvcWebTestClient.bindToApplicationContext(this.webApplicationContext).build();`
- 绑定`RouterFunction`，仅支持`WebFlux`，不支持`Spring MVC`
- 绑定到服务上，`WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();`

## MockMvc

`MockMvc`用来测试`Spring MVC`应用，是服务端的测试框架，配置方式如下：

- 指定一个`Controller`：`MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AccountController()).build();`
- 指定`ApplicationContext`：`MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();`

# spring-boot-test

- `@SpringBootTest`
- `@TestConfiguration`：测试的配置
- `@LocalServerPort`：
- `@MockBean`：用来mock一个Bean
- `@SpyBean`
- `TestRestTemplate`：在Spring5.0提供了一个新的`WebTestClient`可以代替`TestRestTemplate`

## @SpringBootTest

`@SpringBootTest`：和`spring-test`的`@ContextConfiguration`类似，默认添加了`@BootstrapWith(SpringBootTestContextBootstrapper.class)`和`@ExtendWith(SpringExtension.class)`，默认情况下`@SpringBootTest`不会启动web服务，可以通过`webEnvironment`属性来指定怎么运行测试：

- `MOCK`：默认就是该配置，会加载一个web类型的`ApplicationContext`并提供一个mock的web环境
- `RANDOM_PORT`：加载`WebServerApplicationContext`，提供一个真实的web环境，web服务器启动并在一个随机端口上进行监听
- `DEFINED_PORT`：加载`WebServerApplicationContext`，提供一个真实的web环境，web服务器启动并在一个指定的的端口上或者默认8080端口上进行监听
- `NONE`：加载一个`ApplicationContext`，不提供web环境

# spring-boot-test-autoconfigure

- `@AutoConfigureMockMvc`
- `@WebMvcTest`：用来测试`Spring MVC`的Controller
- `@AutoConfigureWebTestClient`
- `@AutoConfigureMetrics`
- `@JsonTest`
- `@WebFluxTest`
- `@GraphQlTest`
- `@DataCassandraTest`
- `@DataCouchbaseTest`
- `@DataElasticsearchTest`
- `@DataJpaTest`
- `@JdbcTest`
- `@DataJdbcTest`
- `@JooqTest`
- `@DataMongoTest`
- `@DataNeo4jTest`
- `@DataRedisTest`
- `@DataLdapTest`
- `@RestClientTest`
- `@AutoConfigureRestDocs`
- `@WebServiceClientTest`

## @WebMvcTest

`@WebMvcTest`会扫描以下方式注册的Bean：`@Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor, WebMvcConfigurer, WebMvcRegistrations, HandlerMethodArgumentResolver`，普通的`@Component`以及`@ConfigurationProperties`方式注册的Bean不会扫描。使用`@EnableConfigurationProperties`注解可以包含`@ConfigurationProperties`注册的Bean。

## @RestClientTest

`@RestClientTest`测试REST客户端，默认会自动配置`Jackson, GSON, Jsonb`、`RestTemplateBuilder`、`MockRestServiceServer`，普通的`@Component`以及`@ConfigurationProperties`方式注册的Bean不会扫描。使用`@EnableConfigurationProperties`注解可以包含`@ConfigurationProperties`注册的Bean。