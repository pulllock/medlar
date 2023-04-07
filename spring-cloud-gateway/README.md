# Spring Cloud Gateway

- Route：路由，由一个ID、一个目标URI、一组断言、一组过滤器做成
- Predicate：断言，是路由转发的判断条件，如果请求与断言匹配成功，则将请求转发到对应的服务
- Filter：过滤器，可对请求进行拦截和修改

常见的Predicate：

| 断言                   | 示例                                                                                                       | 说明                                                        |
| -------------------- | -------------------------------------------------------------------------------------------------------- | --------------------------------------------------------- |
| After                | `- After=2017-01-20T17:42:47.789-07:00[America/Denver]`                                                  | 在指定的时间之后的请求才会被转发到配置的uri上                                  |
| Before               | `- Before=2017-01-20T17:42:47.789-07:00[America/Denver]`                                                 | 在指定的时间之前的请求才会被转发到配置的uri上                                  |
| Between              | `- Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]` | 在指定的时间段之间的请求才会被转发到配置的uri上                                 |
| Cookie               | `- Cookie=chocolate, ch.p`                                                                               | 当请求带Cookie并且Cookie的内容为`chocolate=ch.p`时，当前请求才会被转发到配置的uri上 |
| Header               | `- Header=X-Request-Id, \d+`                                                                             | 当请求头上带属性`X-Request-Id`并且值为整数的请求时，当前请求才会被转发到配置的uri上        |
| Host                 | `- Host=**.somehost.org,**.anotherhost.org`                                                              | 当请求头带有Host并且Host的值能匹配到配置的表达式，当前请求才会被转发到配置的uri上            |
| Method               | `- Method=GET,POST`                                                                                      | 当请求方法是GET和POST的时候请求才会被转发到配置的uri上                          |
| Path                 | `- Path=/red/{segment},/blue/{segment}`                                                                  | 当请求路径与配置的规则匹配时，该请求才能被转发到配置的uri上                           |
| Query                | `- Query=green`                                                                                          | 当请求的查询参数包含green参数的时候，该请求才能被转发到配置的uri上                     |
| RemoteAddr           | `- RemoteAddr=192.168.1.1/24`                                                                            | 当请求的远程地址匹配到配置的规则时，该请求才能被转发到配置的uri上                        |
| Weight               | `Weight=group1, 2`                                                                                       | 表示20%的流量会转发到配置的uri上                                       |
| XForwardedRemoteAddr | `- XForwardedRemoteAddr=192.168.1.1/24`                                                                  | 当请求头中的`X-Forwarded-For`的值能配到配置的规则上，该请求才能被转发到配置的uri上       |

过滤器分类：

| 过滤器类型 | 说明                                                      |
| ----- | ------------------------------------------------------- |
| Pre   | 在请求被转发到服务之前可以对请求进行拦截和修改，例如参数校验、权限校验、流量监控、日志输出以及协议转换等操作。 |
| Post  | 在服务对请求做出响应后可以对响应进行拦截和再处理，例如修改响应内容或响应头、日志输出、流量监控等。       |

- GatewayFilter：应用在单个路由或者一组路由上的过滤器，可以对单个路由或者一组路由上传入的请求和传出响应进行拦截，并实现一些与业务无关的功能，比如登陆状态校验、签名校验、权限校验、日志输出、流量监控等。
- GlobalFilter：应用在所有的路由上的过滤器，可以实现一些统一化的业务功能，例如权限认证、IP 访问限制等。当某个请求被路由匹配时，那么所有的 GlobalFilter 会和该路由自身配置的 GatewayFilter 组合成一个过滤器链。