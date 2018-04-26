package me.cxis.second.kill.dao;

import me.cxis.second.kill.dao.mapper.SuccessKilledMapper;
import me.cxis.second.kill.dao.model.SuccessKilledDO;
import org.springframework.stereotype.Component;

@Component
public class SuccessKilledDao {

    private SuccessKilledMapper successKilledMapper;

    public int insertSuccessKilled(long secondSkillId, long userPhone) {
        return successKilledMapper.insertSuccessKilled(secondSkillId, userPhone);
    }

    public SuccessKilledDO queryBySecondKillId(long secondKillId, long userPhone) {
        return successKilledMapper.queryBySecondKillId(secondKillId, userPhone);
    }
}
