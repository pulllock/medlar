package fun.pullock.cloud.open.feign.async.client.proxy;

import fun.pullock.cloud.open.feign.async.server.api.client.UserClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("${service.spring-cloud-open-feign-eureka-async-server}")
public interface UserClientService extends UserClient {
}
