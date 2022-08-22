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

# 创建示例数据库

使用如下命令创建示例数据库：

```
use spring_boot_mongo
```

在当前数据库中创建用户并授予权限：

```
db.createUser({ user:'spring_boot_mongo_user',pwd:'12345678',roles:[ { role:'readWrite', db: 'spring_boot_mongo'}]});
```

# Mongo命令使用

- `show dbs`：显示所有数据库
- `db`：显示当前数据库对象
- `use test_db`：连接到`test_db`数据库，如果不存在则会创建`test_db`数据库
- `db.dropDatabase()`：删除数据库
- `db.collection.drop()`：删除集合
- `db.createCollection("test_collection")`：创建`test_collection`集合
- `show collectons`或者`show tables`：查看已有集合
- `db.test_collection.insert(Document)`：向`test_collection`集合中插入文档，如果`_id`主键已经存在，则抛异常，不存在则插入数据
- `db.test_collection.save(Document)`：向`test_collection`集合中插入文档，如果`_id`主键存在则更新数据，不存在则插入数据（新版本已废弃）
- `db.test_collection.insertOne()`：向`test_collection`集合中插入一条文档数据
- `db.test_collection.insertMany()`：向`test_collection`集合中插入多条文档数据
- `db.test_collection.update(Query, Update, {upsert: Boolean, multi: Boolean, writeConcern: Document})`：更新`test_collection`集合中的文档
  - Query：更新的条件，类似sql的update的where条件
  - Update：更新的对象，类似sql的update的set后的内容
  - upsert：不存在update的记录时是否插入，true为插入，false为不插入，默认为false
  - multi：默认为false，只更新找到的第一条数据，为true表示更新全部符合条件的数据
  - writeConcern：抛出异常的级别
- `db.test_collection.update(Document, {writeConcern: document})`：使用传入的文档来更新`test_collection`集合中的已有文档，如果`_id`主键存在则更新，不存在就插入
  - Document：更新的文档数据
  - writeConcern：抛出异常的级别
- `db.test_collection.remove(Query, {justOne: Boolean, writeConcern: Document})`：删除`test_collection`集合中的文档
  - Query：删除文档的条件
  - justOne：如果设为true或1，则只删除一个文档，如果不设置该参数，或使用默认值false，则删除所有匹配条件的文档
  - writeConcern：抛出异常的级别
- `db.test_collection.find(Query, Projection)`：查询`test_collection`集合中的文档
  - Query：可选，使用查询操作符指定查询条件
  - Projection：可选，使用投影操作符指定返回的键。查询时返回文档中所有键值，只需省略该参数即可（默认省略）。
- `db.test_collection.find().pretty()`：查询`test_collection`集合中的文档，并使用易读的格式化方式来显示文档
- `db.test_collection.find({"age": 20})`：查询`test_collection`集合中年龄等于20的文档
- `db.test_collection.find({"age": {$lt:20}})`：查询`test_collection`集合中年龄小于20的文档
- `db.test_collection.find({"age": {$lte:20}})`：查询`test_collection`集合中年龄小于等于20的文档
- `db.test_collection.find({"age": {$gt:20}})`：查询`test_collection`集合中年龄大于20的文档
- `db.test_collection.find({"age": {$gte:20}})`：查询`test_collection`集合中年龄大于等于20的文档
- `db.test_collection.find({"age": {$ne:20}})`：查询`test_collection`集合中年龄不等于20的文档
- `db.test_collection.find({key1: value1, key2: value2})`：AND条件查询，传入多个键
- `db.test_collection.find($or: [{key1: value1}, {key2: value2}])`：OR条件查询
- `db.test_collection.find({"age": {$gt:20}, $or: [{"name": "xiaoming"},{"gender": "male"}]})`：AND和OR联合使用
- `db.test_collection.find().limit(100)`：查询并指定获取的数量为100
- `db.test_collection.find().skip(100)`：查询并跳过100条的数据
- `db.test_collection.find().sort(KEY: 1)`：排序，1表示升序，-1表示降序
- `db.test_collection.createIndex(keys, options)`：创建索引
- `db.test_collection.aggregate(AGGREGATE_OPERATION)`：聚合
  - `$sum`：计算总和
  - `$avg`：计算平均值
  - `$min`：获取最小值
  - `$max`：获取最大值
  - `$push`：将值加入一个数组中，不会判断是否有重复的值。
  - `$addToSet`：将值加入一个数组中，会判断是否有重复的值，若相同的值在数组中已经存在了，则不加入。
  - `$first`：获取第一个文档数据
  - `$last`：获取最后一个文档数据
