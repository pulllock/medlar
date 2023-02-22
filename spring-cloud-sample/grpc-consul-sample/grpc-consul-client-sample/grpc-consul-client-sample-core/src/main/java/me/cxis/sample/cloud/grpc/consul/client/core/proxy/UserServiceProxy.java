package me.cxis.sample.cloud.grpc.consul.client.core.proxy;

import me.cxis.sample.cloud.grpc.consul.server.api.service.UserServiceGrpc.UserServiceBlockingStub;
import me.cxis.sample.cloud.grpc.consul.server.api.service.UserServiceOuterClass.User;
import me.cxis.sample.cloud.grpc.consul.server.api.service.UserServiceOuterClass.UserQuery;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceProxy {

    @GrpcClient("grpc-consul-server-sample")
    private UserServiceBlockingStub userServiceBlockingStub;

    public String getUserNameById(int id) {
        UserQuery query = UserQuery.newBuilder().setId(id).build();
        User user = userServiceBlockingStub.getUserByQuery(query);
        return user.getName();
    }
}
