package models;

import interfaces.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import servidor.ChatServer;

/**
 *
 * @author JuanJoo
 */
public class ChatRoomImpl extends UnicastRemoteObject implements ChatRoom {
    private List<Cliente> clients;
    private int capacity;
    
    public ChatRoomImpl(int capacity) throws RemoteException {
        this.clients = new ArrayList<>();
        this.capacity = capacity;
    }

    @Override
    public void login(String username, Cliente client) throws RemoteException {
        if (clients.size() < capacity) {
            clients.add(client);
            broadcastMessage("Server", username + " has joined the chat.");
            updateUserList();
        } else {
            client.receiveMessage("Server: Chat room is full.");
        }
    }

    @Override
    public void logout(String username) throws RemoteException {
        clients.removeIf(client -> {
            try {
                return client.getUsername().equals(username);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        });
        broadcastMessage("Server", username + " has left the chat.");
        updateUserList();
    }

    @Override
    public void sendMessage(String username, String message) throws RemoteException {
        broadcastMessage(username, message);
    }

    @Override
    public List<String> getOnlineUsers() throws RemoteException {
        List<String> usernames = new ArrayList<>();
        for (Cliente client : clients) {
            try {
                usernames.add(client.getUsername());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return usernames;
    }
    
    private void broadcastMessage(String sender, String message) throws RemoteException {
        for (Cliente client : clients) {
            client.receiveMessage(sender + ": " + message);
        }
    }

    public List<Cliente> getClients() {
        return clients;
    }
    
    private void updateUserList() {
         SwingUtilities.invokeLater(() -> ChatServer.updateUserList(getClients()));
    }
}
