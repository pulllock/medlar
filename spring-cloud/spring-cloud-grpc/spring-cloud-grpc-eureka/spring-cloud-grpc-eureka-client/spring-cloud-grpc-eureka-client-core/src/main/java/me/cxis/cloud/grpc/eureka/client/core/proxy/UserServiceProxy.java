package me.cxis.cloud.grpc.eureka.client.core.proxy;

import me.cxis.cloud.grpc.eureka.server.api.service.UserServiceGrpc.UserServiceBlockingStub;
import me.cxis.cloud.grpc.eureka.server.api.service.UserServiceOuterClass.User;
import me.cxis.cloud.grpc.eureka.server.api.service.UserServiceOuterClass.UserQuery;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @GrpcClient("spring-cloud-grpc-eureka-server")
    private UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserQuery query = UserQuery.newBuilder().setId(id).build();
        User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
