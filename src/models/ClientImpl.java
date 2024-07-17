package models;
import interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author JuanJoo
 */
public class ClientImpl extends UnicastRemoteObject implements Cliente{

    private String username;
    private ChatRoom chatRoom;

    public ClientImpl(String username, ChatRoom chatRoom) throws RemoteException {
        this.username = username;
        this.chatRoom = chatRoom;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getUsername() throws RemoteException {
        return username;
    }
    
    public void sendMessage(String message) throws RemoteException {
        chatRoom.sendMessage(username, message);
    }

    public void logout() throws RemoteException {
        chatRoom.logout(username);
    }
}
