package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SuccessKilledDO;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledMapper {

    int insertSuccessKilled(@Param("secondKillId") long secondKillId, @Param("userPhone") long userPhone);

    SuccessKilledDO queryBySecondKillId(@Param("secondKillId") long secondKillId, @Param("userPhone") long userPhone);
}
