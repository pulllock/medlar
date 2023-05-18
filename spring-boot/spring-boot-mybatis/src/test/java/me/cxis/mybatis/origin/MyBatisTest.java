package me.cxis.mybatis.origin;

import me.cxis.mybatis.dao.mapper.ApiDOMapper;
import me.cxis.mybatis.dao.model.ApiDO;
import me.cxis.mybatis.json.Json;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
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
            System.out.println(Json.toJsonString(apiDO));
        }
    }

    @Test
    public void test1() throws IOException {
        String resource = "mybatis/origin/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ApiDOMapper apiDOMapper = session.getMapper(ApiDOMapper.class);
            ApiDO apiDO = apiDOMapper.selectByPrimaryKey( 1L);
            System.out.println(Json.toJsonString(apiDO));
        }
    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis/origin/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream);
        Configuration configuration = xmlConfigBuilder.parse();
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ApiDOMapper apiDOMapper = session.getMapper(ApiDOMapper.class);
            ApiDO apiDO = apiDOMapper.selectByPrimaryKey( 1L);
            System.out.println(Json.toJsonString(apiDO));
        }
    }
}