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

## 哈希（Hash）

- `hdel key field1 [field2]`：删除一个或多个哈希表字段
- `hexists key field`：查找字段是否在哈希表中存在
- `hget key field`：获取哈希表中指定的字段的值
- `hgetall key`：获取哈希表中所有的字段和值
- `hincrby key field increment`：给哈希表中指定的整数字段增加指定的增量
- `hincrbyfloat key field increment`：给哈希表中指定的浮点数字段增加指定的增量
- `hkeys key`：获取哈希表中所有的字段
- `hvals key`：获取哈希表中所有值
- `hlen key`：获取哈希表中字段的数量
- `hmget key field1 [field2]`：获取所有给定的字段的值
- `hmset key field1 value1 [field2 value2]`：同时将多个key-value设置到哈希表中
- `hset key field value`：设置哈希表中的字段为指定的值
- `hsetnx key field value`：只有在指定的字段不存在时才设置哈希表字段的值
- `hscan key cursor [match pattern] [count count]`：迭代哈希表中的键值对

## 列表（List）

- `blpop key1 [key2] timeout`：阻塞移除并获取列表第一个元素，可指定阻塞超时时间
- `brpop key1 [key2] timeout`：阻塞移除并获取列表最后一个元素，可指定阻塞超时时间
- `brpoplpush source destination timeout`：阻塞从列表中弹出一个值，然后将弹出的值插入到另外一个列表中并返回，可指定阻塞超时时间
- `lindex key index`：通过索引获取列表中的元素
- `linsert key before|after pivot value`：在列表的元素的前面或者后面插入元素
- `llen key`：获取列表长度
- `lpop key`：移除并获取列表第一个元素
- `lpush key value1 [value2]`：将一个或多个值插入到列表头部
- `lpushx key value`：将一个值插入到已存在的列表头部，列表不存在时操作无效
- `lrange key start stop`：获取列表指定范围内的元素
- `lrem key count value`：根据参数count的个数，移除列表中与参数value相等的元素
- `lset key index value`：通过索引设置元素的值
- `ltrim key start stop`：保留列表中指定区间内的元素，其他的元素被删除掉
- `rpop key`：移除并获取列表最后一个元素
- `rpoplpush source destination`：移除列表的最后一个元素，并将该元素添加到另一个列表并返回
- `rpush key value1 [value2]`：在列表尾部添加一个或多个值
- `rpushx key value`：将一个值插入到已存在的列表尾部，列表不存在时操作无效

## 集合（Set）

- `sadd key member1 [member2]`：向集合中添加一个或者多个成员
- `scard key`：获取集合中的成员数
- `sdiff key1 [key2]`：返回第一个集合与其他集合之间的差异
- `sdiffstore destination key1 [key2]`：将第一个集合和其他集合之间的差异返回并存储到目标集合中
- `sinter key1 [key2]`：返回给定的所有集合的交集
- `sinterstore destination key1 [key2]`：将给定的所有集合的交集返回并存储在目标集合中
- `sismember key member`：查询member元素是否是集合key的成员
- `smembers key`：返回集合中所有成员
- `smove source destination member`：将member元素从source集合移动到destination集合
- `spop key`：移除并返回集合中的一个随机元素
- `srandmember key [count]`：返回集合中一个或多个随机元素
- `srem key member1 [member2]`：移除集合中一个或多个成员
- `sunion key1 [key2]`：返回给定的所有集合中的并集
- `sunionstore destination key1 [key2]`：将给定的所有集合的并集返回并存储在目标集合中
- `sscan key cursor [match pattern] [count count]`：迭代集合中的元素

## 有序集合（Sorted set）

- `zadd key score1 member1 [score2 member2]`：向有序集合中添加一个或多个成员，或者更新已存在的成员的分数
- `zcard key`：获取有序集合的成员数
- `zcount key min max`：获取在指定的分数区间内的成员数
- `zincrby key increment member`：对指定的成员增加增量
- `zinterstore destination numkeys key [key...]`：将给定的有序集合的交集返回并存储到目标集合中
- `zlexcount key min max`：获取在指定的字典区间内的成员数
- `zrange key start stop [withsocres]`：返回指定区间内的成员
- `zrangebylex key min max [limit offset count]`：获取在指定字段区间内的成员
- `zrangebyscore key min max [withscore] [limit]`：通过分数返回集合中指定区间内的成员
- `zrank key member`：获取成员的索引
- `zrem key member [member...]`：移除一个或者多个成员
- `zremrangebylex key min max`：移除给定的字典区间的所有成员
- `zremrangebyrank key start stop`：移除给定的排名区间的所有成员
- `zremrangebyscore key min max`：移除给定的分数区间的所有成员
- `zrevrange key start stop [withscores]`：返回指定区间内的成员，分数从高到低排序
- `zrevrangebyscore key max min [withscores]`：返回指定分数区间内的成员，分数从高到低排序
- `zrevrank key member`：返回成员的排名，分数从高到低排序
- `zscore key member`：返回成员的分数
- `zunionstore destination numkeys key [key...]`：将给定的有序集合的并集返回并存储到新的目标集合中
- `zscan key cursor [match pattern] [count count]`：迭代有序集合中的元素
