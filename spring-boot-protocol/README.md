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