package me.cxis.mybatis.plugin;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
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
        
        String originSql = (String) metaObject.getValue("delegate.boundSql.sql");
        LOGGER.info("原始sql：{}", originSql);
        
        return null;
    }
}
