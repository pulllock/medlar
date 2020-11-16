package me.cxis.mybatis.plugin;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * 自定义分表插件
 * 假设分了100张表，表的后缀是00、01、...、99
 * 查询的语句中都必须带有用户id，用户id后缀是00、01、...、99
 * 根据用户id后缀来决定使用哪个表
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class SplitTableInterceptor implements Interceptor {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(SplitTableInterceptor.class);
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.info("开始处理分表......");
        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        
        // 获取RoutingStatementHandler对应的MetaObject
        MetaObject metaObject = MetaObject.forObject(
                statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory()
        );

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        
        String originSql = boundSql.getSql();
        LOGGER.info("原始sql：{}", originSql);

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        LOGGER.info("参数：{}", parameterMappings);
        if (CollectionUtils.isEmpty(parameterMappings)) {
            throw new RuntimeException("分表需要有userId参数");
        }

        if (parameterMappings.size() == 1) {
            ParameterMapping parameterMapping = parameterMappings.get(0);
            if (!parameterMapping.getProperty().equals("userId")) {
                throw new RuntimeException("分表需要有userId参数");
            }
            Long userId = (Long) boundSql.getParameterObject();
            LOGGER.info("分表需要的userId：{}", userId);
        } else {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getProperty().equals("userId")) {
                    MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) boundSql.getParameterObject();
                    Long userId = (Long) paramMap.get("userId");
                    LOGGER.info("分表需要的userId: {}", userId);

                    String tableName = "mt_api";
                    LOGGER.info("原始表名：{}", tableName);

                    String userIdStr = String.valueOf(userId);
                    String shardTableName = tableName + "_" +userIdStr.substring(userIdStr.length() - 2);
                    LOGGER.info("路由后的表名：{}", shardTableName);

                    String newSql = originSql.replace(tableName, shardTableName);
                    LOGGER.info("路由后的sql：{}", newSql);

                    metaObject.setValue("delegate.boundSql.sql", newSql);
                }
            }
        }

        return invocation.proceed();
    }
}
