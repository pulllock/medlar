# spring-boot-websocket-webmvc

# 使用

## 使用Chrome的Console作为客户端

1. 启动项目
2. 使用Chrome浏览器的Console当做客户端，打开Chrome的Console，输入：`var ws = new WebSocket("ws://localhost:8080/api/message/websocket")`建立连接
3. 在Chrome的Console中发送消息：`ws.send("你好")`

## 使用wsc作为客户端

1. 安装wsc：`npm install -g wsc`，wsc：`https://github.com/danielstjules/wsc`
2. 启动项目
3. 使用wsc命令连接：`wsc ws://localhost:8080/api/message/websocket`
4. 直接输入消息发送即可