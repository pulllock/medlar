package fun.pullock.mybatis.dao.mapper;

import fun.pullock.mybatis.dao.model.ApiParamDO;

import java.util.List;

public interface ApiParamDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ApiParamDO record);

    int insertSelective(ApiParamDO record);

    ApiParamDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiParamDO record);

    int updateByPrimaryKey(ApiParamDO record);

    List<ApiParamDO> selectByApiId(Long apiId);
}