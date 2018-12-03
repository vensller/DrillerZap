package Server;

import Model.Message;
import Model.MessageType;
import Model.UserConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Ivens
 */
public class CommunicateUserToReloadContacts extends Thread{
    
    private UserConfig user;

    public CommunicateUserToReloadContacts(UserConfig user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {            
            Socket socket = new Socket(user.getIp(), user.getPort());
            socket.setReuseAddress(true);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            output.writeObject(new Message(MessageType.RELOADCONTACTS, ""));
            output.flush();
            
            output.close();
            input.close();
            socket.close();            
        } catch (IOException ex) {
            
        }
    } 
    
}
