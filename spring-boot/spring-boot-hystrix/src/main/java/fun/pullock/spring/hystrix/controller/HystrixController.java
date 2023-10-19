package fun.pullock.spring.hystrix.controller;

import jakarta.annotation.Resource;
import fun.pullock.spring.hystrix.service.HystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hystrix")
public class HystrixController {

    @Resource
    private HystrixService hystrixService;

    @GetMapping( value = "/hello")
    public String hello() {
        return hystrixService.hello();
    }

    @GetMapping(value = "/threadpool")
    public String hystrixThreadPool() {
        return hystrixService.hystrixThreadPool();
    }

    @GetMapping(value = "/semaphore")
    public String hystrixSemaphore() {
        return hystrixService.hystrixSemaphore();
    }

    @GetMapping(value = "/annotation/threadpool")
    public String hystrixAnnotationThreadPool() {
        return hystrixService.hystrixAnnotationThreadPool();
    }

    @GetMapping(value = "/annotation/semaphore")
    public String hystrixAnnotationSemaphore() {
        return hystrixService.hystrixAnnotationSemaphore();
    }

    @GetMapping(value = "/annotation/hello")
    public String hystrixHelloAnnotation() {
        return hystrixService.helloAnnotation();
    }
}
