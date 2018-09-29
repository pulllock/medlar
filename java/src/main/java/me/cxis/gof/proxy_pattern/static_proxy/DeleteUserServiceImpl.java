package me.cxis.gof.proxy_pattern.static_proxy;

public class DeleteUserServiceImpl implements DeleteUserService {

    @Override
    public void deleteUserById(long id) {
        System.out.println("delete user: " + id);
    }
}
