package me.cxis.gof.proxy_pattern.static_proxy;

public class DeleteUserServiceProxy implements DeleteUserService{

    private DeleteUserService deleteUserService = new DeleteUserServiceImpl();

    @Override
    public void deleteUserById(long id) {
        if (checkUser(id)) {
            System.out.println(String.format("User: %s has delete permission", id));
            deleteUserService.deleteUserById(id);
        } else {
            System.out.println(String.format("User: %s has no delete permission", id));
        }

    }

    private boolean checkUser(long id) {
        if (id > 100) {
            return false;
        }
        return true;
    }
}
