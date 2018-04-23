package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SecondKillDO;

import java.util.Date;
import java.util.List;

public interface SecondKillMapper {

    /**
     * 减库存
     * @param secondKillId
     * @param killTime
     * @return
     */
    int reduceNumber(long secondKillId, Date killTime);


    SecondKillDO queryById(long secondKillId);

    List<SecondKillDO> queryAll(int offset, int limit);
}
