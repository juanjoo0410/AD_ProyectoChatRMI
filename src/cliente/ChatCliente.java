package cliente;

import interfaces.*;
import models.ClientImpl;
import java.rmi.Naming;
import java.util.Scanner;

/**
 *
 * @author JuanJoo
 */
public class ChatCliente {

    private static final int PORT = 1099;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = scanner.nextLine();
            ChatRoom chatRoom = (ChatRoom) Naming.lookup("rmi://localhost:"+ PORT + "/ChatRoom");
            ClientImpl client = new ClientImpl(username, chatRoom);

            chatRoom.login(username, client);

            
            String message;
            while (true) {
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("logout")) {
                    chatRoom.logout(username);
                    break;
                }
                chatRoom.sendMessage(username, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
