package fun.pullock.mybatis.service;

import fun.pullock.mybatis.model.Api;
import fun.pullock.mybatis.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;

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

    @Test
    public void testQueryApiByNameMethodAndUserId() {
        Result<Api> result = apiService.queryApiByNameMethodAndUserId("fun.pullock.xxxService", "create", 100002101L);
        System.out.println(result);
    }
}
