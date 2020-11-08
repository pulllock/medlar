package me.cxis.mybatis.plugin.example2;

public class DefaultSqlExecutor implements SqlExecutor {

    @Override
    public Object execute(String sql) {
        System.out.println(String.format("[%s]开始执行：%s", this.getClass().getSimpleName(), sql));
        String result = "User{id=1;name=xxx}";
        System.out.println(String.format("[%s]执行结束：%s；结果：%s", this.getClass().getSimpleName(), sql, result));
        return result;
    }
}
