# 使用docker启动单节点的Consul

- 拉取镜像：`docker pull consul:1.14.4`
- 启动容器：`docker run -d --name consul-server -p 8500:8500 consul:1.14.4 agent -server -bootstrap -bootstrap-expect 1 -ui -client 0.0.0.0 -datacenter local-datacenter`
  - `agent`：表示启动Agent进程
  - `-server`：表示启动Consul Server模式
  - `-bootstrap`：表示这个节点是Server Leader
  - `-bootstrap-expect 1`：指定Consul将等待几个节点组成一个完整的集群，1表示是单节点
  - `-ui`：表示启动Web UI管理
  - `-client 0.0.0.0`：表示对外提供服务任何地址都可以访问
  - `-datacenter`：指定datacenter名称
- 访问管理界面：`http://localhost:8500`