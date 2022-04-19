# Junit5

## Junit5注解列表

- @Test 表示方法是测试方法。与JUnit4的@Test注释不同，这个注释不声明任何属性，因为JUnit Jupiter中的测试扩展基于它们自己的专用注释进行操作。
- @ParameterizedTest 表示方法是参数化测试。
- @RepeatedTest 表示方法是重复测试的测试模板。
- @TestFactory 表示方法是动态测试的测试工厂。
- @TestInstance 用于为带注释的测试类配置测试实例生命周期。
- @TestTemplate 表示方法是为测试用例设计的模板，根据注册提供程序返回的调用上下文的数量进行多次调用。
- @DisplayName 声明测试类或测试方法的自定义显示名称。
- @BeforeEach 表示在当前类中每个@Test、@RepeatedTest、@ParameterizedTest或@TestFactory方法之前执行注释的方法；类似于JUnit 4的@Before。
- @AfterEach 表示在当前类中的每个@Test、@RepeatedTest、@ParameterizedTest或@TestFactory方法之后，都应该执行带注释的方法；类似于JUnit 4的@After。
- @BeforeAll 表示应在当前类中的所有@Test、@RepeatedTest、@ParameterizedTest和@TestFactory方法之前执行带注释的方法；类似于JUnit 4的@BeforeClass。
- @AfterAll 表示在当前类中，所有@Test、@RepeatedTest、@ParameterizedTest和@TestFactory方法都应该执行注释的方法；类似于JUnit 4的@AfterClass。
- @Nested 表示带注释的类是一个嵌套的、非静态的测试类。@BeforeAll和@AfterAll方法不能直接在 @Nested 测试类中使用，除非使用“每个类”测试实例生命周期。
- @Tag 用于在类或方法级别声明过滤测试的标记；类似于TestNG中的测试组或JUnit 4中的类别。
- @Disabled 用于禁用测试类或测试方法；类似于JUnit 4的@Ignore。
- @ExtendWith 用于注册自定义扩展。