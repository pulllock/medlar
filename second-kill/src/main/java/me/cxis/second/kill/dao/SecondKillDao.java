package me.cxis.second.kill.dao;

import me.cxis.second.kill.dao.mapper.SecondKillMapper;
import me.cxis.second.kill.dao.model.SecondKillDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class SecondKillDao {

    @Resource
    private SecondKillMapper secondKillMapper;

    public int reduceNumber(long secondKillId, Date killTime) {
        return secondKillMapper.reduceNumber(secondKillId, killTime);
    }


    public SecondKillDO queryById(long secondKillId) {
        return secondKillMapper.queryById(secondKillId);
    }

    public List<SecondKillDO> queryAll(int offset, int limit) {
        return secondKillMapper.queryAll(offset, limit);
    }

}
