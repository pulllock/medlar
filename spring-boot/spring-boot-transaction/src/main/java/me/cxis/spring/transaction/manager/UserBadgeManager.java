package me.cxis.spring.transaction.manager;

import jakarta.annotation.Resource;
import me.cxis.spring.transaction.dao.mapper.UserBadgeMapper;
import me.cxis.spring.transaction.dao.model.UserBadgeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class UserBadgeManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBadgeManager.class);

    @Resource
    private UserBadgeMapper userBadgeMapper;

    public void addWithoutTransaction() {
        LOGGER.info("addWithoutTransaction");
        userBadgeMapper.insert(createNewUserBadgeDO());
    }

    @Transactional
    public void addWithTransactionDefault() {
        LOGGER.info("addWithTransactionDefault");
        userBadgeMapper.insert(createNewUserBadgeDO());
    }

    private UserBadgeDO createNewUserBadgeDO() {
        UserBadgeDO userBadgeDO = new UserBadgeDO();
        userBadgeDO.setCreateTime(LocalDateTime.now());
        userBadgeDO.setUpdateTime(userBadgeDO.getCreateTime());
        userBadgeDO.setUserId(1L);
        userBadgeDO.setBadgeId(1L);
        userBadgeDO.setCount(1);
        userBadgeDO.setLatestAcquireTime(userBadgeDO.getCreateTime());
        userBadgeDO.setVersion(1);
        return userBadgeDO;
    }
}
