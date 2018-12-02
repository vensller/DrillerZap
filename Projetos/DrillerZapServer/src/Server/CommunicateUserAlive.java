package Server;

import Model.Message;
import Model.MessageType;
import Model.ServerConfig;
import Model.UserConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 *
 * @author Ivens
 */
public class CommunicateUserAlive extends Thread{
    
    private UserConfig user;
    private Boolean userAlive;

    public CommunicateUserAlive(UserConfig user) {
        this.user = user;     
    }

    @Override
    public void run() {
         try {
            Socket socket = new Socket(user.getIp(), user.getPort());
            socket.setSoTimeout(2000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Message message = new Message(MessageType.KEEPALIVE, null);
            output.writeObject(message);
                        
            Message messageInput = (Message) input.readObject();
            
            if (messageInput.getType() != MessageType.ALIVE){
                ServerConfig.getInstance().removeUser(user.getUser());
                userAlive = true;
            }else userAlive = false;
          
        } catch (SocketTimeoutException ex) {
            ServerConfig.getInstance().removeUser(user.getUser());
            userAlive = false;
        } catch (SocketException ex) {            
            ServerConfig.getInstance().removeUser(user.getUser());            
            userAlive = false;
        } catch (IOException ex) {            
        } catch (ClassNotFoundException ex) {            
        }
    }   

    public UserConfig getUser() {
        return user;
    }

    public void setUser(UserConfig user) {
        this.user = user;
    }

    public Boolean getUserAlive() {
        return userAlive;
    }

    
    
    
}
