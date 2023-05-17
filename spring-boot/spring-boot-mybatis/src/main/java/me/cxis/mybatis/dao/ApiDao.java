package me.cxis.mybatis.dao;

import me.cxis.mybatis.dao.mapper.ApiDOMapper;
import me.cxis.mybatis.dao.model.ApiDO;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

@Repository
public class ApiDao {

    @Resource
    private ApiDOMapper apiDOMapper;

    public ApiDO queryByCode(String code) {
        return apiDOMapper.selectByCode(code);
    }

    public ApiDO queryByNameAndMethod(String name, String method) {
        return apiDOMapper.selectByNameAndMethod(name, method);
    }

    public List<ApiDO> queryByName(String name) {
        return apiDOMapper.selectByName(name);
    }

    public ApiDO queryByNameMethodAndUserId(String name, String method, Long userId) {
        return apiDOMapper.selectByNameMethodAndUserId(name, method, userId);
    }
}
