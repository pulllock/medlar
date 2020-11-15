package me.cxis.mybatis.service;

import me.cxis.mybatis.model.Api;
import me.cxis.mybatis.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class APIServiceTest {

    @Resource
    private APIService apiService;

    @Test
    public void testQueryApiByCode() {
        Result<Api> result = apiService.queryApiByCode("1TRW556GGH#TEST");
        System.out.println(result);
    }
}
