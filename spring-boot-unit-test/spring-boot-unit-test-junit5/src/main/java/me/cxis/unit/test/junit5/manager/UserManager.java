package me.cxis.unit.test.junit5.manager;

import me.cxis.unit.test.junit5.dao.UserDao;
import me.cxis.unit.test.junit5.dao.model.UserDO;
import me.cxis.unit.test.junit5.model.UserVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserManager {

    @Resource
    private UserDao userDao;

    public UserVO queryById(Long id) {
        return toUserVO(userDao.selectById(id));
    }

    private UserVO toUserVO(UserDO source) {
        if (source == null) {
            return null;
        }

        UserVO target = new UserVO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setVersion(source.getVersion());
        target.setName(source.getName());
        target.setSex(source.getSex());
        return target;
    }
}
