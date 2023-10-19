package fun.pullock.mybatis.plugin;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fun.pullock.mybatis.json.Json;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * 自定义分表插件
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class SplitTableInterceptor implements Interceptor {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(SplitTableInterceptor.class);

    /**
     * 分表规则，可以配置到动态配置中心或者写到配置文件中等等
     * 当前规则：
     * 根据用户id后缀来决定使用哪个表
     * 假设分了100张表，表的后缀是00、01、...、99
     * 查询的语句中都必须带有用户id，用户id后缀是00、01、...、99
     */
    private final static String SHARDING_RULE = "{\"table\":\"mt_api\",\"key\":\"userId\",\"rule\":\"#userId.substring(#userId.length() - 2)\"}";
    
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

        ObjectNode ruleObject = Json.parseObject(SHARDING_RULE);
        String key = ruleObject.get("key").asText();
        String tableName = ruleObject.get("table").asText();

        // 规则中有配置需要分表
        if (originSql.contains(tableName)) {
            Long userId = null;
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            LOGGER.info("参数：{}", parameterMappings);
            if (parameterMappings == null || parameterMappings.isEmpty()) {
                throw new RuntimeException(String.format("分表需要有%s参数", key));
            }

            if (parameterMappings.size() == 1) {
                ParameterMapping parameterMapping = parameterMappings.get(0);
                if (!parameterMapping.getProperty().equals(key)) {
                    throw new RuntimeException(String.format("分表需要有%s参数", key));
                }
                userId = (Long) boundSql.getParameterObject();
                LOGGER.info("分表需要的{}：{}", key, userId);
            } else {
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    if (parameterMapping.getProperty().equals(key)) {
                        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) boundSql.getParameterObject();
                        userId = (Long) paramMap.get("userId");
                    }
                }
            }

            if (userId == null || userId <= 0) {
                throw new RuntimeException(String.format("分表需要有%s参数", key));
            }

            LOGGER.info("分表需要的{}: {}", key, userId);

            LOGGER.info("原始表名：{}", tableName);

            String rule = ruleObject.get("rule").asText();
            LOGGER.info("分表规则rule: {}", rule);

            String userIdStr = String.valueOf(userId);

            // 解析spel表达式
            ExpressionParser ep= new SpelExpressionParser();
            EvaluationContext ctx = new StandardEvaluationContext();
            ctx.setVariable(key, userIdStr);
            String tableSuffix = ep.parseExpression(rule).getValue(ctx).toString();

            String shardTableName = tableName + "_" + tableSuffix;
            LOGGER.info("路由后的表名：{}", shardTableName);

            String newSql = originSql.replace(tableName, shardTableName);
            LOGGER.info("路由后的sql：{}", newSql);

            metaObject.setValue("delegate.boundSql.sql", newSql);
        }



        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
