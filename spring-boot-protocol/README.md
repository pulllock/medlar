# HTTP

## Header

HTTP1.x中Header字段名称不区分大小写，HTTP2中Header字段名称必须为小写。

## x-forwarded-for

`x-forwarded-for`是一个扩展头，用来表示HTTP请求端的真实IP,格式如下：`x-forwarded-for:client, proxy1, proxy2, ...`

## content-type

`content-type`是实体头，在请求中是客户端告诉服务器实际发送的数据类型，在响应中是告诉客户端实际返回的内容类型，格式：`content-type: [type]/[subtype]; parameter`，比如：

- `content-type: text/html; charset=utf-8`：MIME类型为：`text/html`，编码：`utf-8`
- `content-type: multipart/form-data; boundary=something`：MIME类型为：`multipart/form-data`， `boundary`是分隔符，分隔多个文件、表单项，默认由浏览器自动生成

常见的`content-type`如下：

- `text/plain`：纯文本格式
- `text/xml`：xml格式
- `image/gif`：git图片格式
- `application/json`：json数据格式
- `application/xml`：xml数据格式
- `application/octet-stream`：二进制流数据
- `application/x-www-form-urlencoded`：form表单默认的提交数据格式
- `application/form-data`：表单中进行文件上传时的格式

## content-length

`content-length`是实体头，用来指明发送给接收方的消息体的大小，格式：`content-length: <length>`。

## user-agent

`user-agent`是一个特殊的字符串头，让网络协议对端来识别发起请求的用户代理软件的应用类型、操作系统、软件开发商以及版本号，格式：`user-agent: <product> / <product-version> <comment>`，浏览器通常使用的格式为：

```
user-agent: Mozilla/<version (system-information) <platform> (platform-details) <extensions>
```

- `Mozilla/<version`：现在基本都是固定的：`Mozilla/5.0`
- `(system-information)`：系统信息
- `<platform> (platform-details)`：渲染引擎、版本
- `<extensions>`：扩展，一般都是浏览器的版本以及兼容的信息

chrome示例：

```
Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36
```

- `Mozilla/5.0`：固定
- `(X11; Linux x86_64)`：系统信息，Linux桌面，x86_64版本
- `AppleWebKit/537.36 (KHTML, like Gecko)`：AppleWebKit引擎、引擎版本537.36，`(KHTML, like Gecko)`是为了兼容性
- `Chrome/104.0.0.0`：Chrome浏览器，版本号104.0.0.0

其他示例：

```
Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1

Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36
```

## referer

`referer`包含了当前请求页面的蓝园页面的地址，服务端一般使用`referer`来识别访问来源，格式：`referer: <url>`

## accept

`accept`用来指定客户端能处理的类型，格式如下：

```
accept: <MIME_TYPE>/<MIME_SUBTYPE>

accept: <MIME_TYPE>/*

accept: */*
```

还可以指定多个类型，并为类型指定权重：`Accept: text/html, application/xhtml+xml, application/xml;q=0.9, */*;q=0.8`

## accept-charset

`accept-charset`用来指定客户端可以处理的字符集类型，格式：`accept-charset: <charset>`，也可以指定多个以及权重：`accept-charset: utf-8, iso-8859-1;q=0.5`

## accept-encoding

`accept-encoding`指定客户端能理解的内容编码方式，格式`accept-encoding: gzip`

## accept-language

`accept-language`指定客户端能理解的自然语言，格式如下：

```
Accept-Language: <language>
Accept-Language: *

还可以指定多个以及权重
Accept-Language: fr-CH, fr;q=0.9, en;q=0.8, de;q=0.7, *;q=0.5
```

## accept-ranges

`accept-ranges`服务器使用响应头`accept-ranges`来标识支持的范围请求，浏览器可以通过该响应头来尝试继续下载，而不是重新开始下载，格式：`accept-ranges: bytes`

## range

`range`请求头，用来告知服务器返回文件的哪一部分，格式如下：

```
Range: <unit>=<range-start>-
Range: <unit>=<range-start>-<range-end>

可指定多个
Range: <unit>=<range-start>-<range-end>, <range-start>-<range-end>
Range: <unit>=<range-start>-<range-end>, <range-start>-<range-end>, <range-start>-<range-end>
```

- `unit`：通常是bytes
- `range-start`：整数，范围的起始值
- `range-end`，整数，范围的结束值，如果不指定该值，则表示一直到文档结束

## access-control-allow-credentials

`access-control-allow-credentials`响应头，表示是否可以将对请求的响应暴露给页面

## access-control-allow-headers

`access-control-allow-headers`响应头，指定允许的跨域请求字段

## access-control-allow-methods

`access-control-allow-methods`响应头，指定允许的跨域请求方法

## access-control-allow-origin

`access-control-allow-origin`响应头，指定允许的跨域的请求来源

## access-control-max-age

`access-control-max-age`响应头，指定客户端程序对特定资源的于请求返回结果的缓存时间，单位为秒