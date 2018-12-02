package Server;

import Model.Message;
import Model.MessageType;
import Model.UserConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicateContacts extends Thread{
    
    private UserConfig user;

    public CommunicateContacts(UserConfig userNotAlive) {
        this.user = userNotAlive;
    }

    @Override
    public void run() {
        UserSocketThread socketThread = new UserSocketThread(null);
        Message msg = socketThread.getUserContactsMessage(new Message(MessageType.GIVECONTACTS, user.getUser()));
        List<UserConfig> contacts = (ArrayList<UserConfig>) msg.getMessage();
        for (UserConfig contact : contacts) {            
            try {
                Socket socket = new Socket(user.getIp(), user.getPort());
                socket.setSoTimeout(2000);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                Message message = new Message(MessageType.CONTACTNOTALIVE, contact);
                output.writeObject(message);   
                
                output.close();
                input.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(CommunicateContacts.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }    
    
    
}
