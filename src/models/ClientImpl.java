package models;
import cliente.ChatClient;
import interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.SwingUtilities;

/**
 *
 * @author JuanJoo
 */
public class ClientImpl extends UnicastRemoteObject implements Cliente{

    private String username;
    private ChatRoom chatRoom;
    private ChatClient app;

    public ClientImpl(String username, ChatRoom chatRoom) throws RemoteException {
        this.username = username;
        this.chatRoom = chatRoom;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        if (app != null) {
            SwingUtilities.invokeLater(() -> {
                app.addChat(message);
            });
        }
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

    public void setApp(ChatClient app) {
        this.app = app;
    }
}
