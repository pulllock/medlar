# spring-boot-k8s

# 安装Kubernetes

- 安装docker desktop

- 打开docker desktop的settings

- 选择Kubernetes，将Enable Kubernetes和Show system containers(advanced)都勾选

- 点击Apply & Restart按钮进行安装重启，需要等待下载完成

# 安装Kubernetes Dashboard

1. 使用命令安装：`kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml`

2. 安装完后使用命令查看：`kubectl get pods -n kubernetes-dashboard -w`

3. 创建服务账号：`dashboard-admin`，所属命名空间：`kubernetes-dashboard`，执行以下命令：`kubectl create serviceaccount dashboard-admin -n kubernetes-dashboard`

4. 给服务账号`dashboard-admin`绑定`admin`角色，执行以下命令：`kubectl create clusterrolebinding dashboard-cluster-admin --clusterrole=cluster-admin --serviceaccount=kubernetes-dashboard:dashboard-admin`

5. 创建Token并指定过期时间：`kubectl -n kubernetes-dashboard create token dashboard-admin --duration 3153600000s`，记录下Token：`eyJhbGciOiJSUzI1NiIsImtpZCI6IkgtcXk3Vm9VdlpxSEhUVG5SMkhZUkI1X3E2dmJsYjdZWmZpbWVPOGlzdlUifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjo0ODE3NDQ3MTU5LCJpYXQiOjE2NjM4NDcxNTksImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJkYXNoYm9hcmQtYWRtaW4iLCJ1aWQiOiIzZjc1YWM3MS01M2NkLTQ2MTgtYTkyZC1lYzBiZTgxYTkzZmQifX0sIm5iZiI6MTY2Mzg0NzE1OSwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmVybmV0ZXMtZGFzaGJvYXJkOmRhc2hib2FyZC1hZG1pbiJ9.WmsJ3nUERnpb0ynWDKWzFOda5YPfihfUe0MZU6gZS69UaxShw9_tsM1eNgn826lRyq_HYs8ZQoDh8GrYVgDqvXaDIUKchA-FM_2ZP3VBiYuXrcl5HBaR8O3AmhsA_VlcW3J-HH0MpATKTZeAE5r-Xe6tT2QDttWsfGNn3ubEE0smO-WDAVJl1BhSNhGTU_HpjoQJEdSP9ZfFg2gIUPdPfFYylnvxMr3VacMCVUGAVfhSWW5SVtTGmcKFaS4xndiTMPCAj_-T4Trw3GZMHMCZ-mucDvA_my7HPLy0aNojoA5frmfm3u6yU53lTcDWsy7Wui55ZF563oAqbLxy6bFe3A`

6. 启动Dashboard：`kubectl proxy`

7. 访问Dashboard：`[Kubernetes Dashboard](http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/)`，使用上面的Token即可进入到Dashboard

# 安装ingress-nginx

1. 使用命令安装：`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.7.0/deploy/static/provider/cloud/deploy.yaml`
2. 使用命令查看安装进度并等待完成：`kubectl get pods --namespace=ingress-nginx --watch`

如果安装不成功，可以使用如下方式：

1. 下载配置文件`https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.7.0/deploy/static/provider/cloud/deploy.yaml`
2. 编辑配置文件，将配置文件中的`registry.k8s.io`替换为国内镜像，比如：`registry.lank8s.cn`
3. 使用命令安装：`kubectl apply -f deploy.yaml`
4. 使用命令查看安装进度并等待完成：`kubectl get pods --namespace=ingress-nginx --watch`

安装成功后继续进行验证：

1. 创建一个测试用web应用部署：`kubectl create deployment demo --image=httpd --port=80`
2. 暴露成服务：`kubectl expose deployment demo`
3. 创建ingress资源：`kubectl create ingress demo-localhost --class=nginx --rule="demo.localdev.me/*=demo:80"`
4. 转发本地端口到ingress控制器：`kubectl port-forward --namespace=ingress-nginx service/ingress-nginx-controller 8080:80`
5. 访问：`http://demo.localdev.me:8080`，页面返回`It works!`，表示成功

验证成功后删除临时的验证资源：

1. 删除ingress资源：`kubectl delete ingress demo-localhost`
2. 删除服务：`kubectl delete service demo`
3. 删除应用部署：`kubectl delete deployment demo`

# 将应用部署到k8s中

## 创建应用

创建一个应用：`spring-boot-k8s-demo-app`，并添加测试代码

## 应用容器化

1. 项目根目录创建Dockerfile，内容参考`spring-boot-k8s-demo-app/Dockerfile`
2. 将`spring-boot-k8s-demo-app`项目进行打包，执行命令：`mvn clean package -Dmaven.test.skip=true -U`
3. 创建应用的docker镜像：`docker build -t spring-boot-k8s-demo-app:1.0.0-SNAPSHOT .`
4. 启动应用容器：`docker run -d -p 8080:8080 --name spring-boot-docker-app spring-boot-k8s-demo-app:1.0.0-SNAPSHOT`
5. 测试是否能访问应用的测试接口
6. 测试完成后，删除容器：`docker container rm --force spring-boot-docker-app`

## 应用部署到kubernetes中

1. 在应用中创建目录：`k8s`，并在`k8s`目录下创建`deployment.yaml`文件，内容参考：`spring-boot-k8s-demo-app/k8s/deployment.yaml`
2. 部署应用：`kubectl apply -f deployment.yaml`
3. 列出部署：`kubectl get deployments`
4. 将deployment.yaml中配置的域名`mylocal.com`配置到host中，映射到`127.0.0.1`
4. 使用配置的域名访问测试应用：`http://mylocal.com/api/spring-boot-k8s-demo-app/k8s/demo/query`
5. 测试完成后如果要删除应用可使用：`kubectl delete -f deployment.yaml`

# Kubernetes组件

- Control Plane，控制平面，管理集群中的工作节点和Pod，也称为master node（master节点）或者head node（头节点）
  - kube-apiserver，该组件负责公开Kubernetes API，处理接收请求的工作，是Kubernetes控制平面的前端。跟踪所有集群组件的状态并管理他们之间的交互
  - etcd，存储Kubernetes所有集群数据的数据库，可以是控制平面的一部分，也可以在外部进行配置
  - kube-scheduler，负责监视新创建的Pod、还未指定运行节点的Pod，选择节点来让Pod运行在上面
  - kube-controller-manager
    - Node Controller，节点控制器，负责节点出现故障时进行通知和响应
    - Job Controller，任务控制器，监测代表一次性任务的Job对象，创建Pod来运行这些任务
    - EndpointSlice controller，端点分片控制器，
    - ServiceAccount controller，服务账号控制器，为新的命名空间创建默认的服务账号（ServiceAccount）
  - cloud-controller-manager，云平台控制
    - Node Controller，节点控制器
    - Route Controller，路由控制器
    - Service Controller，服务控制器
- Node，节点，也称worker node（工作节点）或者计算节点，会运行容器化的应用程序
  - kubelet，在每个节点上运行，保证容器都运行在Pod中，kubelet会接收一组PodSpecs，确保这些PodSpecs中描述的容器处于运行状态且健康，充当apiserver和Node之间的管道
  - kube-proxy，在每个节点上运行的网络代理，实现Kubernetes种Service（服务）概念的一部分。维护节点上的网络规则，网络规则会允许集群内部或外部的网络会话与Pod进行网络通信
  - Container Runtime，容器运行时，负责运行容器的软件，Kubernetes支持多种运行时环境：containerd、CRI-O以及实现了CRI接口的其他容器
- Addons，插件
  - DNS
  - Dashboard