package Server;

import Model.Message;
import Model.MessageType;
import Model.ServerConfig;
import Model.UserConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicateUserAlive extends Thread{
    
    private UserConfig user;

    public CommunicateUserAlive(UserConfig user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(user.getIp(), user.getPort());
            socket.setSoTimeout(1000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Message message = new Message(MessageType.KEEPALIVE, null);
            output.writeObject(message);
            
            Message messageInput = (Message) input.readObject();
            
            if (messageInput.getType() != MessageType.ALIVE){
                ServerConfig.getInstance().removeUser(user);
            }            
            
        } catch (IOException ex) {
            Logger.getLogger(CommunicateUserAlive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicateUserAlive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
}
