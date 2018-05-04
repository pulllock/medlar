package me.cxis.rpc.test2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by cheng.xi on 17-2-24.
 */
public interface UserService extends Remote {
    User getUserByName(String name) throws RemoteException;
}
