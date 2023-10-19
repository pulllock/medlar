package fun.pullock.mybatis.dao;

import fun.pullock.mybatis.dao.mapper.ApiParamDOMapper;
import fun.pullock.mybatis.dao.model.ApiParamDO;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import java.util.List;

@Repository
public class ApiParamDao {

    @Resource
    private ApiParamDOMapper apiParamDOMapper;

    public List<ApiParamDO> queryByApiId(Long apiId) {
        return apiParamDOMapper.selectByApiId(apiId);
    }
}


