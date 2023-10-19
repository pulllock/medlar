package fun.pullock.groovy.controller;

import fun.pullock.groovy.manager.BizManager;
import fun.pullock.groovy.model.SubmitParam;
import fun.pullock.groovy.model.SubmitResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
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

    @GetMapping("/submit2")
    public SubmitResult submit2() {
        SubmitParam param = new SubmitParam();
        param.setAge(new Random().nextInt(40));
        return bizManager.submit2(param);
    }
}
