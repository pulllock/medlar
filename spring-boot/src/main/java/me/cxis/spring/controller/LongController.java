package me.cxis.spring.controller;

import com.alibaba.fastjson.JSON;
import me.cxis.spring.model.LongModel;
import me.cxis.spring.param.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/long")
public class LongController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongController.class);

    @GetMapping("/get")
    public Long get(@RequestParam Long id) {
        LOGGER.info("test long get param id: {}", id);
        Long returnValue = 90306268518825984L;
        LOGGER.info("test long get return value: {}", returnValue);
        return returnValue;
    }

    @PostMapping("/post")
    public LongModel post(@RequestBody LongParam param) {
        LOGGER.info("test long post param: {}", JSON.toJSONString(param));
        LongModel result = new LongModel();
        result.setId(90306268518825984L);
        LOGGER.info("test long post result: {}", JSON.toJSONString(result));
        return result;
    }

    public static void main(String[] args) {
        LongModel result = new LongModel();
        result.setId(90306268518825984L);
        LOGGER.info("test long post result: {}", JSON.toJSONString(result));
    }
}
