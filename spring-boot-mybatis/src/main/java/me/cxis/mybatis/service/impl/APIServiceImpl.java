package me.cxis.mybatis.service.impl;

import me.cxis.mybatis.manager.ApiManager;
import me.cxis.mybatis.model.Api;
import me.cxis.mybatis.result.Result;
import me.cxis.mybatis.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("apiService")
public class APIServiceImpl implements APIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIServiceImpl.class);

    @Resource
    private ApiManager apiManager;


    @Override
    public Result<Api> queryApiByCode(String code) {
        Api api = apiManager.queryByCode(code);
        return new Result<>(api);
    }

    @Override
    public Result<Api> queryApiByNameAndMethod(String name, String method) {
        Api api = apiManager.queryByNameAndMethod(name, method);
        return new Result<>(api);
    }

    @Override
    public Result<List<Api>> queryApiByName(String name) {
        List<Api> apis = apiManager.queryByName(name);
        return new Result<>(apis);
    }
}