package fun.pullock.mybatis.dao.mapper;

import fun.pullock.mybatis.dao.model.ApiDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ApiDO record);

    int insertSelective(ApiDO record);

    ApiDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiDO record);

    int updateByPrimaryKey(ApiDO record);

    ApiDO selectByCode(String code);

    ApiDO selectByNameAndMethod(@Param("name") String name, @Param("method") String method);

    List<ApiDO> selectByName(String name);

    ApiDO selectByNameMethodAndUserId(
            @Param("name") String name,
            @Param("method") String method,
            @Param("userId") Long userId
    );
}