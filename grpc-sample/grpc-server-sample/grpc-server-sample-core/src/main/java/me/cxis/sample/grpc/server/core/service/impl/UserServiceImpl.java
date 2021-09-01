package me.cxis.sample.grpc.server.core.service.impl;

import io.grpc.stub.StreamObserver;
import me.cxis.sample.grpc.server.api.service.UserServiceGrpc;
import me.cxis.sample.grpc.server.api.service.UserServiceOuterClass;
import me.cxis.sample.grpc.server.api.service.UserServiceOuterClass.User;
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
