package fun.pullock.mybatis.plugin;

import fun.pullock.mybatis.json.Json;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})
})
public class SqlPrintInterceptor implements Interceptor {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(SqlPrintInterceptor.class);
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            LOGGER.info("将要执行的sql：{}", statementHandler.getBoundSql().getSql());
        }
        long startTime = System.currentTimeMillis();
        Object object = invocation.proceed();
        long endTime = System.currentTimeMillis();
        LOGGER.info("执行花费的时间：{}ms", (endTime - startTime));
        return object;
    }
    
    @Override
    public void setProperties(Properties properties) {
        LOGGER.info("属性：{}", Json.toJsonString(properties));
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
