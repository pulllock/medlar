package fun.pullock.cloud.open.feign.client.proxy;

import jakarta.annotation.Resource;
import fun.pullock.cloud.open.feign.server.api.model.User;
import fun.pullock.cloud.open.feign.server.api.model.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Component
public class UserServiceProxy {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service.spring-cloud-loadbalancer-server}")
    private String serverUrl;

    public User queryUserById(Long userId) {
        try {
            ResponseEntity<Result<User>> response = restTemplate.exchange("http://" + serverUrl + "/user/queryByUserId?userId=" + userId, GET, null,  new ParameterizedTypeReference<Result<User>>(){});
            if (!response.hasBody()) {
                return null;
            }
            Result<User> result = response.getBody();
            if (result == null || !result.isSuccess()) {
                return null;
            }
            return result.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
