package me.cxis.mybatis.plugin.example1;

public class Test {

    public static void main(String[] args) {
        SqlExecutor sqlExecutor = new DefaultSqlExecutor();
        SqlExecutor proxy = SqlExecutorProxy.getProxy(sqlExecutor);
        Object result = proxy.execute("select * from user where user_id = 1");
        System.out.println(result);
    }
}
