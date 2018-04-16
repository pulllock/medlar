package me.cxis.spring.controller;

import me.cxis.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getUserName(@RequestParam int userId) {
        return userService.getUserName(userId);
    }

    @RequestMapping(value = "/async/deferredResult/name", method = RequestMethod.GET)
    public DeferredResult<String> asyncDeferredResultGetUserName(@RequestParam int userId) {
        DeferredResult<String> result = new DeferredResult<>();
        dealInAnotherThread(result);
        return result;
    }

    @RequestMapping(value = "/async/callable/name", method = RequestMethod.GET)
    public Callable<String> asyncCallableGetUserName(@RequestParam int userId) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "xiaoming";
            }
        };
    }

    @RequestMapping(value = "async/webAsyncTask/name", method = RequestMethod.GET)
    public WebAsyncTask<String> asyncWebAsyncTaskGetUserName(@RequestParam int userId) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "xiaohong";
            }
        };
        WebAsyncTask task = new WebAsyncTask<>(1000, callable);
        task.onTimeout(new Callable() {
            @Override
            public Object call() throws Exception {
                return "timeout";
            }
        });
        return task;
    }

    private void dealInAnotherThread(DeferredResult<String> result) {
        try {
            Thread.sleep(2000);
            result.setResult("xiaoming");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
