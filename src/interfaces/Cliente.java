package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JuanJoo
 */
public interface Cliente extends Remote{
    void receiveMessage(String message) throws RemoteException;
    String getUsername() throws RemoteException;
}
