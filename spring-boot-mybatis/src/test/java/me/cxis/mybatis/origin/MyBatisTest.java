package me.cxis.mybatis.origin;

import com.alibaba.fastjson.JSON;
import me.cxis.mybatis.dao.model.ApiDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test() throws IOException {
        String resource = "mybatis/origin/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ApiDO apiDO = session.selectOne("me.cxis.mybatis.dao.mapper.ApiDOMapper.selectByPrimaryKey", 1L);
            System.out.println(JSON.toJSONString(apiDO));
        }
    }
}
