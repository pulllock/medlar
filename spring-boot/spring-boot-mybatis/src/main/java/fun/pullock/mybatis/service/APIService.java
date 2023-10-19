package fun.pullock.mybatis.service;

import fun.pullock.mybatis.model.Api;
import fun.pullock.mybatis.result.Result;

import java.util.List;

public interface APIService {

    /**
     * 根据code查询api
     * @param code api唯一编码
     * @return code对应的api
     */
    Result<Api> queryApiByCode(String code);

    /**
     * 根据name和method查询api
     * @param name api名称
     * @param method 方法名
     * @return api
     */
    Result<Api> queryApiByNameAndMethod(String name, String method);

    /**
     * 根据name查询api
     * @param name api名称
     * @return api集合
     */
    Result<List<Api>> queryApiByName(String name);

    /**
     * 根据name和method以及userId查询api
     * @param name api名称
     * @param method 方法名
     * @param userId 用户id
     * @return
     */
    Result<Api> queryApiByNameMethodAndUserId(String name, String method, Long userId);
}
