# 说明

redis示例

# 使用Docker安装redis并启动

- 拉取最新镜像：`docker pull redis:latest`
- 运行容器：`docker run -itd --name redis -p 6379:6379 redis`
- 使用`redis-cli`连接redis服务并测试，先进入bash：`docker exec -it redis /bin/bash`，然后输入命令：`redis-cli`

# Redis命令

## 字符串（String）

- `set key value`：设置键key的值为value
- `setex key seconds value`：设置key的值为value，并设置key的过期时间为seconds秒
- `psetex key milliseconds value`：设置key的值为value，并设置key的过期时间为milliseconds毫秒
- `setnx key value`：key不存在时设置key的值为value
- `get key`：获取key的值
- `setrange key offset value`：从指定的偏移量开始将key的值替换为value
- `getrange key start end`：返回key中字符串的子串
- `getset key value`：将key的值设置为value，并返回旧的value
- `getbit key offset`：获取key的值的指定偏移量上的位（bit）
- `setbit key offset value`：对key的值在指定的偏移量上进行设置或者清除位（bit）
- `mget key1 [key2...]`：获取多个key的值
- `mset key value [key value...]`：同时设置多个key-value
- `msetnx key value [key value...]`：当所有的给定的key都不存在时，同时设置多个key-value
- `strlen key`：返回key的值的长度
- `incr key`：将key中存储的数字类型的值加1
- `incrby key increment`：将key的值加上给定的增量
- `incrbyfloat key increment`：将key的值加上给定的浮点增量
- `decr key`：将key中存储的数字类型的值减1
- `decrby key decrement`：将key的值减去给定的减量
- `append key value`：如果key已经存在并且是一个字符串，将指定的value追加到原来的value后面


## 键（key）

- `del key`：删除key
- `dump key`：序列化key，并返回被序列化的值
- `exists key`：检查key是否存在
- `expire key seconds`：给key设置过期时间（秒）
- `expireat key timestamp`：给key设置过期时间（unix时间戳）
- `pexpire key milliseconds`：给key设置过期时间（毫秒）
- `pexpireat key milliseconds-timestamp`：给key设置过期时间（unix毫秒时间戳）
- `keys pattern`：查找所有符合给定模式的key
- `move key db`：将key移动到给定的db中
- `persist key`：移除key的过期时间
- `pttl key`：返回key的剩余的过期时间（毫秒）
- `ttl key`：返回key的剩余的过期时间（秒）
- `randomkey`：随机返回一个key
- `rename key newkey`：修改key的名字
- `renamenx key newkey`：当新名字不存在时，将key改名为新名字
- `scan cursor [match pattern] [count count]`：迭代数据库中的数据库键
- `type key`：返回key所存储的值的类型