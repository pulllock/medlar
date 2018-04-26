package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SuccessKilledDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SuccessKilledMapperTest {

    @Resource
    private SuccessKilledMapper successKilledMapper;

    @Test
    public void insertSuccessKilled() {
        int result = successKilledMapper.insertSuccessKilled(1000, 17721020000L);
        System.out.println(result);
    }

    @Test
    public void queryBySecondKillId() {
        SuccessKilledDO successKilledDO = successKilledMapper.queryBySecondKillId(1000, 17721020000L);
        System.out.println(successKilledDO);
    }
}