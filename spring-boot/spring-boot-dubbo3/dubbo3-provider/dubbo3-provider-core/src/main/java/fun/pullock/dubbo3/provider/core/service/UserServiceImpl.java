package fun.pullock.dubbo3.provider.core.service;

import fun.pullock.dubbo3.provider.core.json.Json;
import io.grpc.stub.StreamObserver;
import fun.pullock.dubbo3.provider.api.service.DubboUserServiceGrpc.IUserService;
import fun.pullock.dubbo3.provider.api.service.DubboUserServiceGrpc.UserServiceImplBase;
import fun.pullock.dubbo3.provider.api.service.UserServiceOuterClass.User;
import fun.pullock.dubbo3.provider.api.service.UserServiceOuterClass.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends UserServiceImplBase implements IUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void getUserByQuery(UserQuery request, StreamObserver<User> responseObserver) {
        LOGGER.info("get user by query, request: {}", Json.toJsonString(request));
        User user = User
                .newBuilder()
                .setId(123)
                .setAge(23)
                .setName("xiao ming")
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
