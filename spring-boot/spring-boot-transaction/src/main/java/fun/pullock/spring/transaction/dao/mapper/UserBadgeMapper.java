package fun.pullock.spring.transaction.dao.mapper;

import fun.pullock.spring.transaction.dao.model.UserBadgeDO;

public interface UserBadgeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserBadgeDO row);

    int insertSelective(UserBadgeDO row);

    UserBadgeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBadgeDO row);

    int updateByPrimaryKey(UserBadgeDO row);
}