package me.cxis.controller;

import me.cxis.model.User;
import me.cxis.service.UserService;
import me.cxis.service.impl.UserServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("")
public class UserController {

    private UserService userService = new UserServiceImpl();

    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public User getUserById(@PathParam("id") long id) {
        return userService.getUserById(id);
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
