# 说明

MongoDB示例

# 使用Docker安装并启动MongoDB

- 获取官方最新版本镜像：`docker pull mongo:latest`
- 运行MongoDB容器：`docker run -itd --name mongo -p 27017:27017 mongo --auth`，这里`--auth`是使用密码才能访问服务

执行如下命令进入MongoDB容器：

```
docker exec -it mongo mongo admin
```

进入之后会有如下的提示：

```
MongoDB shell version v5.0.5
connecting to: mongodb://127.0.0.1:27017/admin?compressors=disabled&gssapiServiceName=mongodb
Implicit session: session { "id" : UUID("b17fc272-fb21-4ffd-90a4-81bc9ac37bad") }
MongoDB server version: 5.0.5
================
Warning: the "mongo" shell has been superseded by "mongosh",
which delivers improved usability and compatibility.The "mongo" shell has been deprecated and will be removed in
an upcoming release.
For installation instructions, see
https://docs.mongodb.com/mongodb-shell/install/
================
Welcome to the MongoDB shell.
For interactive help, type "help".
For more comprehensive documentation, see
	https://docs.mongodb.com/
Questions? Try the MongoDB Developer Community Forums
	https://community.mongodb.com
```

这样就进入了MongoDB shell，继续执行以下命令添加用户并设置密码：

```
db.createUser({ user:'admin',pwd:'12345678',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
```

执行成功后会有如下的提示：

```
Successfully added user: {
    "user" : "admin",
    "roles" : [
        {
            "role" : "userAdminAnyDatabase",
            "db" : "admin"
        },
        "readWriteAnyDatabase"
    ]
}
```

尝试使用设置的用户信息进行连接：

```
db.auth('admin', '12345678');
```

会返回1，表示成功了。也可以使用其他的GUI工具来进行连接。

