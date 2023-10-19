package fun.pullock.spring.transaction.dao.mapper;

import fun.pullock.spring.transaction.dao.model.UserBadgeLogDO;

public interface UserBadgeLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserBadgeLogDO row);

    int insertSelective(UserBadgeLogDO row);

    UserBadgeLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBadgeLogDO row);

    int updateByPrimaryKey(UserBadgeLogDO row);
}