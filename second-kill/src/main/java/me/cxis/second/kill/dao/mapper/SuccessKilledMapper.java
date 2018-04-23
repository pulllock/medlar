package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SuccessKilledDO;

public interface SuccessKilledMapper {

    int insertSuccessKilled(long secondSkillId, long userPhone);

    SuccessKilledDO queryBySecondKillId(long secondKillId);
}
