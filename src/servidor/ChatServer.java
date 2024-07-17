package servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import models.ChatRoomImpl;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

/**
 *
 * @author JuanJoo
 */
public class ChatServer {
    private static final int PORT = 1099;
    private static final int ROOM_SIZE = 10;
    
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(PORT);
            ChatRoomImpl chatRoom = new ChatRoomImpl(ROOM_SIZE);
            Naming.rebind("rmi://localhost:" + PORT + "/ChatRoom", chatRoom);
            System.out.println("Chat server is running...");
            
            // Hilo para mostrar la lista de usuarios conectados cada 10 segundos
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000); // 10 segundos
                        chatRoom.printOnlineUsers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            
        } catch (RemoteException e) {
            System.err.println("Error: Fallo al crear el registro RMI o al exportar el objeto remoto.");
        } catch (MalformedURLException e) {
            System.err.println("Error: URL mal formada al registrar el objeto remoto.");
        } catch (Exception e) {
            System.err.println("Error inesperado al iniciar el servidor.");
        }
    }
}
