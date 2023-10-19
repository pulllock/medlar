package fun.pullock.cloud.grpc.eureka.client.core.proxy;

import fun.pullock.cloud.grpc.eureka.server.api.service.UserServiceGrpc;
import fun.pullock.cloud.grpc.eureka.server.api.service.UserServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @GrpcClient("spring-cloud-grpc-eureka-server")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserServiceOuterClass.UserQuery query = UserServiceOuterClass.UserQuery.newBuilder().setId(id).build();
        UserServiceOuterClass.User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
