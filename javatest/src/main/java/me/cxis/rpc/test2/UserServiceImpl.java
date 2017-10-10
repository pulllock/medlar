package me.cxis.rpc.test2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService{
    protected UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public User getUserByName(String name) throws RemoteException {
        System.out.println("服务端方法开始被调用了");
        User user = new User();
        user.setName("H-" + name);
        return user;
    }
}
