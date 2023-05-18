package me.cxis.grpc.nacos.client.core.proxy;

import me.cxis.grpc.nacos.server.api.service.UserServiceGrpc.UserServiceBlockingStub;
import me.cxis.grpc.nacos.server.api.service.UserServiceOuterClass.User;
import me.cxis.grpc.nacos.server.api.service.UserServiceOuterClass.UserQuery;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @GrpcClient("grpc-nacos-server")
    private UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserQuery query = UserQuery.newBuilder().setId(id).build();
        User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
