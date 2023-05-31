# spring-boot-nginx

# nginx文件和目录

- `/etc/nginx/`：nginx的默认配置目录

- `/etc/nginx/nginx.conf`：该文件是nginx的默认配置文件

- `/etc/nginx/conf.d/`：该目录包含默认的HTTP服务器配置文件，以`.conf`结尾的文件都包含在`/etc/nginx/nginx.conf`文件的顶层http代码块中。

- `/var/log/nginx`：是nginx的默认日志位置

# nginx命令

- `nginx -h`：显示帮助信息

- `nginx -v`：显示nginx版本

- `nginx -V`：显示nginx版本、build信息和配置参数

- `nginx -t`：测试nginx配置

- `nginx -T`：测试nginx配置并将验证后的配置打印出来

- `nginx -s signal`：向master进程发送信号，可以发送：stop、quit、reload、reopen等：
  
  - stop：立即停止nginx进程
  
  - quit：完成当前正在处理的请求后停止进程
  
  - reload：重新加载配置
  
  - reopen：重新打开日志文件

# nginx配置

- 全局配置

- http配置

- server配置

# nginx全局配置

全局配置在`nginx.conf`文件中，示例内容如下：

```nginx
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}
```

- `user`：用来配置worker进程的用户和组，如果忽略group，则group的名字等于user参数配置的用户的用户组

- `worker_processes`：worker进程启动的数量，这些进程用于处理客户的所有连接

- `error_log`：错误日志文件，该配置的第二个参数用来指定错误级别（debug、info、notice、warn、error、crit、alert、emerg），debug级别的错误只有在编译时配置了`--with-debug`选项时才可以使用

- `pid`：设置记录主进程ID的文件

- `events`：
  
  - `use`：使用什么样的连接方法
  
  - `worker_connections`：工作进程能接受的最大并发连接数

# nginx的http配置

http配置在`nginx.conf`文件中，示例内容如下：

```nginx
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}
```

- `include`：可以指定配置文件

- 客户端指令，用于处理客户端连接本身
  
  - `keepalive_timeout`：指定`keep-alive`连接持续多久

- 文件IO指令，控制nginx如何投递静态文件以及如何管理文件描述符
  
  - `sendfile`：使用`sendfile(2)`直接复制数据从一个到另一个文件描述符

- hash指令，控制nginx分配给某些变量多大的静态内存

- socket指令，描述nginx如何设置创建TCP套接字的变量选项
  
  - `tcp_nopush`：

- `access_log`：访问日志，第一个参数是日志文件存储的路径；第二个可选参数指定`log_format`配置中设置的日志格式，默认是combined格式；第三个可选参数指明了写缓存的大小

- `log_format`：日志格式

- `log_not_found`：禁止在错误日志中报告404错误，默认为on

- `log_subrequest`：禁止在访问日志中启用记录子请求，默认为off

- `open_log_file_cache`：

# nginx的server配置

server定义一个虚拟主机，server配置示例：

```nginx
server {
    listen       80;
    listen  [::]:80;
    server_name  localhost;

    #access_log  /var/log/nginx/host.access.log  main;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
}
```

- `listen`：定义一个IP地址/端口组合或者是UNIX域套接字路径，格式：
  
  - `listen address[:port]`
  
  - `listen port`
  
  - `listen unix:path`

- `server_name`：虚拟服务名字

- `location`：有两种语法：
  
  - `location [=|~~|~~*|^~~|!~~|!~*] uri {...}`
  
  - `location @name {...}`：命名location仅对内部访问重定向，只能在server级别定义

## server_name匹配规则

server_name可以使用如下几种：

- 确切的名字
- 通配符
- 正则表达式

如果名字可以匹配多个，则按如下的优先级进行查找，并选中第一个匹配的：

1. 确切的名字
2. 最长的以星号起始的通配符名字，比如：`*.example.com`
3. 最长的以星号结束的通配符名字，比如：`mail.*`
4. 第一个匹配的正则表达式的名字

### 通配符

通配符星号只能在名字的开头或者结尾，并且星号和其他字符之间必须用点分割。星号可以匹配多个域名部分，比如`*.example.com`既可以匹配`www.example.com`也可以匹配`www.sub.example.com`。`.example.org`是特殊的通配符格式，可以同时匹配确切名称`example.com`和通配符名称`*.example.com`。

### 正则表达式

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

### 特殊匹配格式

- `server_name "";`：匹配Host请求头不存在的情况
- `server_name "_";`：无任何意义
- `server_name "*";`：它被错误地解释为万能的名称。它从不用作通用或通配符服务器名称。相反，它提供了server_name_in_redirect指令现在提供的功能。现在不建议使用特殊名称*，而应使用server_name_in_redirect指令。 

## location匹配规则

location语法规则：`location [=|~|~*|^~|!~|!~*] uri {...}`，各部分含义如下：

- `=`：等号表示精确匹配
- `~`：表示区分大小写正则匹配
- `~*`：表示不区分大小写正则匹配
- `^~`：表示URI以某个常规字符串开头
- `!~`：表示区分大小写正则不匹配
- `!~*`：表示不区分大小写正则不匹配
- `uri`：具体的URI。如果没有上面的各种匹配规则，只有URI部分并且是`/`，则表示通用匹配，任何请求都是匹配到。

多个location的匹配规则：

- 首先精确匹配
- 其次匹配`^~`
- 再试按照顺序正则匹配
- 最后是`/`通用匹配

# 反向代理

## http代理模块

- `proxy_pass`：指定请求被传递到上游的服务器，格式为URL

- `proxy_connect_timeout`：指明nginx从接受请求到连接至上游服务器的最长等待时间

- `proxy_cookie_domain`：替代从上游服务器来的`Set-Cookie`头中的domain属性，domain属性被替换为一个字符串、一个正则表达式或者是引用的变量

- `proxy_cookie_path source target`：替代从上游服务器来的`Ser-Cookie`头中的path属性，path属性被替换为一个字符串、一个正则表达式或者是引用的变量

- `proxy_headers_hash_bucket_size`：指定头名字的最大值

- `proxy_headers_hash_max_size`：指定从上游服务器接收到头的总大小

- `proxy_hide_header`：指定不应该传递给客户端头的列表

- `proxy_http_version`：指定用于同上游服务器通信的HTTP协议版本

- `proxy_ignore_client_abort`：如果设置为on，当客户端放弃连接后，nginx不会放弃同上游服务器的连接

- `proxy_ignore_headers`：当处理来自上游服务器的响应时，该指令设置哪些头可以被忽略

- `proxy_intercept_errors`：如果启用，nginx会显示配置的error_page错误，而不是来自于上游服务器的直接响应

- `proxy_max_temp_file_size`：在写入内存缓冲区时，当响应与内存缓冲区不匹配时，该指令给出溢出文件的最大值

- `proxy_pass_header`：会覆盖掉`proxy_hide_header`中设置的头，允许这些头传递到客户端

- `proxy_pass_request_body`：如果设置为off，则会阻止请求体发送到上游服务器

- `proxy_pass_request_headers`：，如果设置为off，则会阻止请求头发送到上游服务器

- `proxy_`read_timeout：连接关闭前从上游服务器两次成功的读操作耗时

- `proxy_redirect`：重写来自上游服务器的Location和Refresh头

- `proxy_set_body`：发送到上游服务器的请求体可能会被该设置值修改

- `proxy_set_header`：重写发送到上游服务器头的内容

- `proxy_temp_file_write_size`：限制在同一时间内缓冲到一个临时文件的数据量，使得nginx不会过长的阻止单个请求

- `proxy_`temp_path：设定临时文件的缓冲，用于缓冲从上游服务器来的文件，可以设定目录的层次

## http upstream模块

upstream定义一组上游服务器

- `server`：定义一个服务器地址（带有TCP端口号的域名、IP地址、UNIX域套接字）和可选参数，参数如下：
  - `weight`：设置服务器的权重
  - `max_fails`：设置在`fail_timeout`时间之内尝试对一个服务器连接的最大次数，如果超过这个次数就会被标记为down
  - `fail_timeout`：服务响应的超时时间，如果超过这个超时时间，服务器会被标记为down
  - `backup`：备份服务器
  - `down`：该参数标记一个服务器不再接受任何请求
- `ip_hash`：通过IP地址的哈希值确保客户端均匀的连接所有服务器
- `keepalive`：指定worker进程缓存到上游服务器的连接数
- `least_conn`：会激活负载均衡算法，将请求发送到活跃连接数最少的那台服务器

### 负载均衡

upstream模块有三种负载均衡：

- round-robin（轮询）：默认的算法
- IP hash（IP哈希）：需要通过`ip_hash`配置激活
- Least Connection（最少连接数）：需要通过`least_conn`配置激活

# http模块的预定义变量

| 变量                    | 说明                                                            |
| --------------------- | ------------------------------------------------------------- |
| `$arg_name`           | 请求中name参数                                                     |
| `$args`               | 所有请求参数                                                        |
| `$binary_remote_addr` | 客户端IP地址的二进制格式                                                 |
| `$content_length`     | 请求头Content-Length的值                                           |
| `$content_type`       | 请求头Content-Type的值                                             |
| `$cookie_name`        | cookie标签名字                                                    |
| `$document_root`      | 当前请求中root或者alias的值                                            |
| `$document_uri`       | `$uri`的别名                                                     |
| `$host`               | 如果当前有Host，该变量为请求头的Host的值；如果没有Host，则该变量等于匹配该请求的`server_name`的值 |
| `$hostname`           | 运行nginx主机的主机名                                                 |
| `$http_name`          | 请求头name的值                                                     |
| `$https`              | 如果是通过SSL连接的，则该值为on，否则为空字符串                                    |
| `$is_args`            | 如果请求有参数则该值为?，否则为空字符串                                          |
| `$limit_rate`         |                                                               |
| `$nginx_version`      | nginx版本                                                       |
| `$pid`                | worker进程的ID                                                   |
| `$query_string`       | `$args`的别名                                                    |
| `$realpath_root`      |                                                               |
| `$remote_addr`        | 客户端IP地址                                                       |
| `$remote_port`        | 客户端端口                                                         |
| `$remote_user`        | 使用http基本认证时的用户名                                               |
| `$request`            | 从客户端收到的完整请求，包括http请求方法、URI、http协议、头、请求体                       |
| `$request_body`       | 请求体                                                           |
| `$request_body_file`  |                                                               |
| `$request_completion` | 如果请求完成，则该值为OK，否则为空字符串                                         |
| `$request_method`     | 请求使用的http方法                                                   |
| `$request_uri`        | 请求的URI                                                        |
| `$schema`             | 当前请求的协议                                                       |
| `$sent_http_name`     | 响应头名字的值                                                       |
| `$server_addr`        | 接受请求服务器的地址                                                    |
| `$server_name`        | 接受请求的虚拟主机`server_name`的值                                      |
| `$server_port`        | 接受请求的服务器端口                                                    |
| `$server_protocol`    | 当前请求中使用的HTTP协议                                                |
| `$status`             | 响应状态                                                          |
| `$tcpinfo_rtt`        |                                                               |
| `$tcpinfo_rttvar`     |                                                               |
| `$tcpinfo_snd_cwnd`   |                                                               |
| `$tcpinfo_rcv_space`  |                                                               |
| `$uri`                | 当前请求的表转化URI                                                   |