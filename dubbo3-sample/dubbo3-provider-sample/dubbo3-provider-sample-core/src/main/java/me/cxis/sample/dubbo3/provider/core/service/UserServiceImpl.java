package me.cxis.sample.dubbo3.provider.core.service;

import com.alibaba.fastjson.JSON;
import io.grpc.stub.StreamObserver;
import me.cxis.sample.dubbo3.provider.api.service.UserServiceGrpc.IUserService;
import me.cxis.sample.dubbo3.provider.api.service.UserServiceOuterClass.User;
import me.cxis.sample.dubbo3.provider.api.service.UserServiceOuterClass.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void getUserByQuery(UserQuery request, StreamObserver<User> responseObserver) {
        LOGGER.info("get user by query, request: {}", JSON.toJSONString(request));
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
