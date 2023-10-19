package fun.pullock.cloud.open.feign.client.proxy;

import fun.pullock.cloud.open.feign.server.api.controller.IUserController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("${service.okhttp-server}")
public interface UserService extends IUserController {
}
