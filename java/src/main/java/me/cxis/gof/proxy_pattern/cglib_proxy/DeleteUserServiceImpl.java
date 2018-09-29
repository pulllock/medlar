package me.cxis.gof.proxy_pattern.cglib_proxy;

public class DeleteUserServiceImpl implements DeleteUserService {

    @Override
    public void deleteUserById(long id) {
        System.out.println(String.format("Delete user: %s", id));
    }
}
