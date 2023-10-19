package fun.pullock.grpc.nacos.client.core.proxy;

import fun.pullock.grpc.nacos.server.api.service.UserServiceGrpc;
import fun.pullock.grpc.nacos.server.api.service.UserServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @GrpcClient("grpc-nacos-server")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserServiceOuterClass.UserQuery query = UserServiceOuterClass.UserQuery.newBuilder().setId(id).build();
        UserServiceOuterClass.User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
