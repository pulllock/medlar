package me.cxis.spring.transaction.manager;

import jakarta.annotation.Resource;
import me.cxis.spring.transaction.dao.mapper.UserBadgeLogMapper;
import me.cxis.spring.transaction.dao.model.UserBadgeLogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class UserBadgeLogManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBadgeLogManager.class);

    @Resource
    private UserBadgeLogMapper userBadgeLogMapper;

    public void addWithoutTransaction() {
        LOGGER.info("addWithoutTransaction");
        userBadgeLogMapper.insert(createNewUserBadgeLogDO());
    }

    @Transactional
    public void addWithTransactionDefault() {
        LOGGER.info("addWithTransactionDefault");
        userBadgeLogMapper.insert(createNewUserBadgeLogDO());
    }

    private UserBadgeLogDO createNewUserBadgeLogDO() {
        UserBadgeLogDO userBadgeLogDO = new UserBadgeLogDO();
        userBadgeLogDO.setCreateTime(LocalDateTime.now());
        userBadgeLogDO.setUpdateTime(userBadgeLogDO.getCreateTime());
        userBadgeLogDO.setUserId(1L);
        userBadgeLogDO.setBadgeId(1L);
        userBadgeLogDO.setVersion(1);
        userBadgeLogDO.setAcquireTime(userBadgeLogDO.getCreateTime());
        return userBadgeLogDO;
    }
}
