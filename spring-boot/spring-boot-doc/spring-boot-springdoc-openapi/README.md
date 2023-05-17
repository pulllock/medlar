# springdoc-openapi

# 访问

- Swagger UI界面：`http://localhost:8080/swagger-ui.html`
- OpenAPI描述文档：`http://localhost:8080/v3/api-docs`
- yaml格式文档：`http://localhost:8080/v3/api-docs.yaml`

# 配置

- `springdoc.swagger-ui.path=/swagger-ui.html`：自定义swagger-ui路径
- `springdoc.api-docs.path=/api-docs`：自定义api-doc路径
- `springdoc.show-actuator=true`：在UI界面中显示Actuator相关的端点
- `springdoc.use-management-port=true, management.endpoints.web.exposure.include=openapi, swagger-ui`：让swagger-ui使用管理的端口
  - 访问UI界面：`http://localhost:9090/actuator/swagger-ui`
- `springdoc.api-docs.enabled=false`：禁用/v3/api-docs端点
- `springdoc.swagger-ui.enabled=false`：禁用swagger-ui
- `springdoc.packagesToScan=com.package1, com.package2`：扫描的包
- `springdoc.pathsToMatch=/v1, /api/balance/**`：匹配的路径