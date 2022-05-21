# Groovy

## 字符串

- 插值表达式：`def firstName = "H" println("name is: ${firstName}")`
- 单引号字符串是纯文本，不支持插值表达式；三单引号字符串是纯文本，可以跨多行，不支持插值表达式
- 双引号字符串，支持插值表达式；三双引号字符串，可以跨多行，支持插值表达式
- 斜线字符串：`def str = /xxxxx/`，支持跨多行
- $字符串：`def str = $/xxxxxx/$`，支持跨多行

## 列表

- 定义一个list：`def list = [1, 2, 3]`
- 使用下标获取list中元素：`list[0]`
- 使用反向索引获取list中元素`list[-1]`
- 向列表中添加元素：`list << 4`
- 使用多个下标获取元素：`list[1, 3]`
- 使用索引范围获取元素：`list[2..4]`

## 数组

- 声明数组：`String[] array = ['a', 'b', 'c']`
- 声明数组：`def array = [1, 2, 3] as int[]`

## map

- map默认使用LinkedHashMap初始化
- 定义一个map：`def map = [:]`
- 定义一个map：`def map = [a: 1, b: 2, c: 3]`
- 使用key获取map中元素：`map['a']`
- 使用key获取map中元素：`map.a`
- 使用变量作为map的key，需要给变量名加上括号：`def map = [(key): 'value']`

## 运算符

- `==` 相当于`equals()`方法
- `!=` 不同
- `===` 强等于，Groovy3.0.0开始，使用对象的`is()`来比较，is方法用来判断引用相同
- `!==` 强不等于，使用对象的`is()`来比较
- Elvis（猫王）运算符：`user.name ? user.name : 'NoName'`，等价于：`user.name ?: 'NoName'`
- Elvis（猫王）运算符：`name = name ? name : 'NoName'`，等价于：`name ?= 'NoName'`
- 对象运算符的安全引用运算符：`?.`，如果引用的对象为null，会返回null，不会抛空指针异常。`def obj = null println(obj?.name)`
- 获取对象的属性：`obj.name`，默认是用的是`obj.getName()`getter方法来获取属性
- 获取对象的属性：`obj.@name`，`.@`强制直接使用属性，不用getter方法
- 方法指针运算符：`.&`，可以得到对象的方法的引用：`def str = 'a' def func = str.&toUpperCase`可得到str对象的toUpperCase方法的引用
- 方法引用运算符：`::`，比如：`BigInteger::add`
- 展开运算符：`*.`，用于调用聚合对象中所有项，将每一个项的结果存储在列表中
- 方法展开运算符：`*变量名`，展开方法参数
- 列表展开运算符：`*列表变量名`，展开列表
- Map展开运算符：`*:变量名`，展开Map
- 范围运算符：`..`
- compareTo比较运算符：`<==>`
- 下标运算符：`[]`
- 安全下标运算符：`?[]`
- 从属关系运算符：`in`，如果是列表，则相当于调用contains方法
- 强转运算符：`()`，是as的特殊变种
- call运算符：`()`，用于隐式的调用call方法，对于任何定义call方法的对象，都可以忽略`.call()`直接使用`()`

## 构造函数

- `def user = new User('Jack', 20)`
- `def user = new User(name: 'Jack', age: 20)`
- `def user = ['Jack', 20] as User`
- `User user = ['Jack', 20]`

## 字段和属性

- 调用对象的内置属性`properties`可以获得全部定义的属性

## 读文件

- 一次性读取文本文件：`String textContent = new File("/path/to/file/example.txt").text`
- 按行读取文本文件：`List<String> textContents = new File("/path/to/file/example.txt").readLines()`
- 读取xml文件：`def xmlRoot = new XmlSlurper().parse('/path/to/file/example.xml')`
- 读取json文件：`def jsonRoot = new JsonSlurper().parse(new File('/path/to/file/example.json'))`