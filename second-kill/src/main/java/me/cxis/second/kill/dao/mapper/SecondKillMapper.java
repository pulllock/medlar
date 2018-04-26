package me.cxis.second.kill.dao.mapper;

import me.cxis.second.kill.dao.model.SecondKillDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecondKillMapper {

    /**
     * 减库存
     * @param secondKillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("secondKillId") long secondKillId, @Param("killTime") Date killTime);


    SecondKillDO queryById(long secondKillId);

    List<SecondKillDO> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
