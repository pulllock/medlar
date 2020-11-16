package me.cxis.mybatis.manager;

import me.cxis.mybatis.dao.ApiDao;
import me.cxis.mybatis.dao.ApiParamDao;
import me.cxis.mybatis.dao.model.ApiDO;
import me.cxis.mybatis.dao.model.ApiParamDO;
import me.cxis.mybatis.model.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class ApiManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiManager.class);

    @Resource
    private ApiDao apiDao;

    @Resource
    private ApiParamDao apiParamDao;

    public Api queryByCode(String code) {
        ApiDO apiDO = apiDao.queryByCode(code);
        if (apiDO == null) {
            return null;
        }
        return toApi(apiDO);
    }

    public Api queryByNameAndMethod(String name, String method) {
        ApiDO apiDO = apiDao.queryByNameAndMethod(name, method);
        if (apiDO == null) {
            return null;
        }
        return toApi(apiDO);
    }

    public List<Api> queryByName(String name) {
        List<ApiDO> apiDOS = apiDao.queryByName(name);
        if (CollectionUtils.isEmpty(apiDOS)) {
            return null;
        }
        return apiDOS
                .stream()
                .map(this::toApi)
                .collect(toList());
    }

    public Api queryByNameMethodAndUserId(String name, String method, Long userId) {
        ApiDO apiDO = apiDao.queryByNameMethodAndUserId(name, method, userId);
        if (apiDO == null) {
            return null;
        }
        return toApi(apiDO);
    }

    private Api toApi(ApiDO source) {
        Api target = new Api();
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
