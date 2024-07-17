package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public interface ChatRoom extends Remote {

    void login(String username, Cliente client) throws RemoteException;

    void logout(String username) throws RemoteException;

    void sendMessage(String username, String message) throws RemoteException;

    List<String> getOnlineUsers() throws RemoteException;
}
