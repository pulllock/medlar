package fun.pullock.cloud.grpc.eureka.server.core.service.impl;

import io.grpc.stub.StreamObserver;
import fun.pullock.cloud.grpc.eureka.server.api.service.UserServiceGrpc;
import fun.pullock.cloud.grpc.eureka.server.api.service.UserServiceOuterClass;
import fun.pullock.cloud.grpc.eureka.server.api.service.UserServiceOuterClass.User;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserByQuery(UserServiceOuterClass.UserQuery request, StreamObserver<User> responseObserver) {
        System.out.println("get user by query: " + request);
        User result = User.newBuilder().setName("xxxxxName").build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
