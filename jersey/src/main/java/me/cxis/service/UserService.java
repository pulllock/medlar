package me.cxis.service;

import me.cxis.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    List<User> getAllUsers();
}
