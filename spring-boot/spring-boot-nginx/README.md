# spring-boot-nginx

# server_name匹配规则

server_name可以使用如下几种：

- 确切的名字
- 通配符
- 正则表达式

如果名字可以匹配多个，则按如下的优先级进行查找，并选中第一个匹配的：

1. 确切的名字
2. 最长的以星号起始的通配符名字，比如：`*.example.com`
3. 最长的以星号结束的通配符名字，比如：`mail.*`
4. 第一个匹配的正则表达式的名字

## 通配符

通配符星号只能在名字的开头或者结尾，并且星号和其他字符之间必须用点分割。星号可以匹配多个域名部分，比如`*.example.com`既可以匹配`www.example.com`也可以匹配`www.sub.example.com`。`.example.org`是特殊的通配符格式，可以同时匹配确切名称`example.com`和通配符名称`*.example.com`。

## 正则表达式

nginx正则表达式必须以波浪线`~`开头，需要添加`^`和`$`锚定符号，比如：`~^www\d+\.example\.com$`，如果需要匹配`.`则需要用反斜杠`\`进行转义，如果正则匹配中有`{`和`}`，则需要用双引号引起来。

命名的正则表达式捕获组可以在后面作为变量使用，如果如下的捕获组`domain`：

```
server {
    server_name   ~^(www\.)?(?<domain>.+)$;

    location / {
        root   /sites/$domain;
    }
}
```

捕获组的格式有如下：

- `?<捕获组名字>`
- `?'捕获组名字'`
- `?P<捕获组名字>`

正则表达式捕获组也可以使用数字进行引用，如下：

```
server {
    server_name   ~^(www\.)?(.+)$;

    location / {
        root   /sites/$2;
    }
}
```

## 特殊匹配格式

- `server_name "";`：匹配Host请求头不存在的情况
- `server_name "_";`：无任何意义
- `server_name "*";`：它被错误地解释为万能的名称。 它从不用作通用或通配符服务器名称。相反，它提供了server_name_in_redirect指令现在提供的功能。 现在不建议使用特殊名称*，而应使用server_name_in_redirect指令。 