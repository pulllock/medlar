package me.cxis.cloud.open.feign.client.proxy;

import me.cxis.cloud.open.feign.server.api.controller.IUserController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("${service.spring-cloud-loadbalancer-server}")
public interface UserService extends IUserController {
}
