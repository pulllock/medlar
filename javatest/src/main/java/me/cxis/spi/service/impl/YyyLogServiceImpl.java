package me.cxis.spi.service.impl;

import me.cxis.test.spi.service.CommonLogService;

/**
 * Created by cheng.xi on 2017-03-03 19:54.
 */
public class YyyLogServiceImpl implements CommonLogService {
    @Override
    public void printLog(String log) {
        System.out.println("这是Yyy公司实现的log模块，" + log);
    }
}
