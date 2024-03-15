package fun.pullock.cloud.open.feign.async.server.api.client;

import fun.pullock.cloud.open.feign.async.server.api.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserClient {

    @GetMapping("/user/queryById")
    User queryById(@RequestParam("userId") Long userId);
}
