package me.cxis.cloud.open.feign.client.proxy;

import me.cxis.cloud.open.feign.server.api.controller.IUserController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("spring-cloud-open-feign-server-core")
public interface UserService extends IUserController {
}
