package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SecondKillDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SecondKillMapperTest {

    @Resource
    private SecondKillMapper secondKillMapper;

    @Test
    public void reduceNumber() {
        int result = secondKillMapper.reduceNumber(1000, new Date());
        System.out.println(result);
    }

    @Test
    public void queryById() {
        long id = 1000;
        SecondKillDO secondKillDO = secondKillMapper.queryById(id);
        System.out.println(secondKillDO);
    }

    @Test
    public void queryAll() {
        List<SecondKillDO> secondKillDOS = secondKillMapper.queryAll(0, 100);
        secondKillDOS.forEach(System.out::println);
    }
}