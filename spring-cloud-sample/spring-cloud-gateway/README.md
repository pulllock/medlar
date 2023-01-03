# Spring Cloud Gateway

- Route：路由，由一个ID、一个目标URI、一组断言、一组过滤器做成
- Predicate：断言，是路由转发的判断条件，如果请求与断言匹配成功，则将请求转发到对应的服务
- Filter：过滤器，可对请求进行拦截和修改

常见的Predicate断言：

| 断言    | 示例                                                         | 说明                                                         |
| ------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Path    | `- Path=/user/list/** `                                      | 当请求路径与`/user/list/** `匹配时，该请求才能被转发到配置的uri上。 |
| Before  | `- Before=2023-01-03T23:00:02.100+08:00[Asia/Shanghai]`      | 在2023年01月03日23时00分02.100秒之前的请求，才会被转发到配置的uri上。 |
| After   | `- After=2023-01-03T23:00:02.100+08:00[Asia/Shanghai]`       | 在2023年01月03日23时00分02.100秒之后的请求，才会被转发到配置的uri上。 |
| Between | `- Between=2023-01-03T23:00:02.100+08:00[Asia/Shanghai],2023-01-04T23:00:02.100+08:00[Asia/Shanghai]` | 在2023年01月03日23时00分02.100秒到在2023年01月04日23时00分02.100秒之间的请求，才会被转发到配置的uri服务器上。 |
| Cookie  | `- Cookie=name,xxxx`                                         | 携带Cookie且Cookie的内容为name=xxxx的请求，才会被转发到配置的uri上。 |
| Header  | `- Header=X-Request-Id,\d+`                                  | 请求头上携带属性X-Request-Id且属性值为整数的请求，才会被转发到配置的uri上。 |
| Method  | `- Method=GET`                                               | 只有GET请求才会被转发到配置的uri上。                         |

过滤器分类：

| 过滤器类型 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| Pre        | 在请求被转发到服务之前可以对请求进行拦截和修改，例如参数校验、权限校验、流量监控、日志输出以及协议转换等操作。 |
| Post       | 在服务对请求做出响应后可以对响应进行拦截和再处理，例如修改响应内容或响应头、日志输出、流量监控等。 |

- GatewayFilter：应用在单个路由或者一组路由上的过滤器，可以对单个路由或者一组路由上传入的请求和传出响应进行拦截，并实现一些与业务无关的功能，比如登陆状态校验、签名校验、权限校验、日志输出、流量监控等。
- GlobalFilter：应用在所有的路由上的过滤器，可以实现一些统一化的业务功能，例如权限认证、IP 访问限制等。当某个请求被路由匹配时，那么所有的 GlobalFilter 会和该路由自身配置的 GatewayFilter 组合成一个过滤器链。