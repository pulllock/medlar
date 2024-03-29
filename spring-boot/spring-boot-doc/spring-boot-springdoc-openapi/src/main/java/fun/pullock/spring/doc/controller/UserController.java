package fun.pullock.spring.doc.controller;

import fun.pullock.spring.doc.model.User;
import fun.pullock.spring.doc.param.UserQueryParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Tag(name = "UserController", description = "用户相关的接口，包括：查询接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "查询用户", description = "根据输入参数查询用户")
    @PostMapping("/query")
    public List<User> queryUsers(@RequestBody UserQueryParam param) {
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        return users;
    }

    @Operation(summary = "根据用户ID查询用户", description = "根据输入的用户ID参数查询用户")
    @GetMapping("/queryById")
     public User queryUserById(@Parameter(name = "userId", description = "用户id", required = true, in = QUERY) @RequestParam Long userId) {
        return new User();
     }
}
