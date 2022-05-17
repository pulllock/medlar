package me.cxis.groovy.controller;

import me.cxis.groovy.manager.BizManager;
import me.cxis.groovy.model.SubmitParam;
import me.cxis.groovy.model.SubmitResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/biz")
public class BizController {

    @Resource
    private BizManager bizManager;

    @GetMapping("/process")
    public void process() {
        bizManager.process();
    }

    @GetMapping("/submit")
    public SubmitResult submit() {
        SubmitParam param = new SubmitParam();
        param.setAge(new Random().nextInt(40));
        return bizManager.submit(param);
    }
}
