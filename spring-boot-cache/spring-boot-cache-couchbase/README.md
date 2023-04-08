# spring-boot-cache-couchbase

# 使用docker安装Couchbase

- 拉取镜像：`docker pull couchbase:community-7.1.1`
- 运行容器：`docker run -d --name couchbase -p 8091-8097:8091-8097 -p 9123:9123 -p 11207:11207 -p 11210:11210 -p 11280:11280 -p 18091-18097:18091-18097 couchbase:community-7.1.1`
- 访问控制台进行配置：`http://localhost:8091`
  - Setup New Cluster
  - Cluster Name: SpringBootCacheCouchbase
  - Create Admin Username: Administrator
  - Create Password/Confirm Password: 12345678
  - Accept terms & conditions
  - Configure Disk, Memory, Services
  - Host Name/IP Address: 127.0.0.1
  - Service Memory Quotas 保持默认
  - Buckets -> ADD BUCKET
    - Name: SpringBootCacheCouchbaseTest
    - 其他保持默认