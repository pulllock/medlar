package me.cxis.spring.controller;

import me.cxis.spring.chain.GatewayHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chain")
public class ChainController {

    @Resource
    private List<GatewayHandler> gatewayHandlers;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam String str) {
        GatewayHandler defaultHandler = gatewayHandlers.get(0);
        return defaultHandler.process(str);
    }
}
