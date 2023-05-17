# Spock

## Spock块

- given: 初始化
- setup: 代替given
- when: 调用测试方法
- then: 检验测试结果
- and: 其他块的补充
- expect: then:的简化
- where: 参数化测试
- cleanup: 释放资源

## 使用

- `>>>`在Spock中为stub/mock提供特定返回值：`def userService = Mock(UserService) userService.getUserName() >>> ['xiao ming', 'xiao hong']`，第一次调用返回xiao ming，第二次调用返回xiao hong
- `>>>`在Spock中为stub/mock提供特定返回值：`def userService = Mock(UserService) userService.getUserName() >>> 'xiao ming' >>> 'xiao hong'`，第一次调用返回xiao ming，第二次调用返回xiao hong
- `@Subject`标明被测试类
- `@Title`描述
- `@Narrative`大段的描述
- `setup()`该方法在每个测试方法运行前执行
- `cleanup()`该方法在每个测试方法运行后执行
- `setupSpec()`该方法在每个类运行前执行
- `cleanupSpec()`该方法在每个类运行后执行
- `@Shared`表示对象生命周期是整个测试类
- `with(){}`可将断言合为一组
- `@Unroll`将where块的每一条当做一个单独方法来执行，`Unroll(字符串)`中的字符串中可以使用`#参数名字`来替换参数
- 数据管道，where块中可以使用`<<`来进行数据数的输入：`number << (10..20)`
- `_`下划线表示任意值
- `@Stepwise`按照编写顺序执行
- `thrown()`抛异常
- `notThrown()`没有抛异常
- `@Issue()`关联Issue，可在注解中写文本或者URL或者数组形式的
- `@Timeout`超时
- `@Ignore`排除
- `@IgnoreRest`排除其它的
- `@IgnoreIf({})`实现动态的排除
- `@Requires({})`也是动态的决定要不要执行
- `@AutoCleanup("xxx")`自动资源清理，默认自动调用`close()`方法，也可以在注解中自定义xxx方法
- `Spy()`可进行部分mock，Spy主要用在遗留代码中，使用到Spy的时候可能意味着代码是有问题的
- Spock不支持Mockito和PowerMock的`@InjectMocks`和`@Mock`组合，可使用`spock-subjects-collaborators-extension`的`@Subject`和`@Collaborator`代替`@InjectMocks`和`@Mock`

### stub/mock

stub/mock指定方法的返回值：

```
UserService userService = Stub(UserService)
userService.add() >> true
```

stub/mock使用with将多个方法设置组合到一起：

```
UserService userService = Stub(UserService) {
    add() >> true
    remove() >> false
    update() >> true
}
```

stub/mock抛异常：

```
UserService userService = Stub(UserService)
userService.add() >> {throw new RuntimeException("xxxx")}
```

stub/mock使用闭包返回动态返回值：

```
UserService userService = Stub(UserService)
userService.add() >> {...}
```

验证mock方法执行次数：

```
1 * userService.add() // 执行一次

0 * userService.add() // 未执行

_ * userService.add() // 任意次
```

验证mock方法的参数：

```
1 * userService.add(!null, 1) // 执行一次，第一个参数不为null，第二个参数为1
1 * userService.add(_ as String, _ as int) // 执行一次，第一个参数是String类型，第二个参数是int类型
```

使用闭包验证mock方法的参数：

```
1 * userService.add({....}, {.....})
```

### 集成测试

#### 使用Spring

- Spring的`@Sql("xxx.sql")`可在测试方法运行前自动执行SQL
- Spring的`@Transactional和@Rollback`可进行回滚

#### 使用Groovy的SQL操作数据库

```
@Autowired
DataSrouce dataSrouce // 注入数据源

def "test"() {
    Sql sql = new Sql(dataSrouce)
    sql.execute("select * from user;")
}
```

#### REST服务

- 可使用Spring的RestTemplate
- 可使用Groovy的RESTClient