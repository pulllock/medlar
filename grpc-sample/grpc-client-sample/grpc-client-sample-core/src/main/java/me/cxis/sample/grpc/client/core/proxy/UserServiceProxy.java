package me.cxis.sample.grpc.client.core.proxy;

import me.cxis.sample.grpc.server.api.service.UserServiceGrpc.UserServiceBlockingStub;
import me.cxis.sample.grpc.server.api.service.UserServiceOuterClass.User;
import me.cxis.sample.grpc.server.api.service.UserServiceOuterClass.UserQuery;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    // @GrpcClient("grpc-server-sample")
    @GrpcClient("local-grpc-server")
    private UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserQuery query = UserQuery.newBuilder().setId(id).build();
        User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
