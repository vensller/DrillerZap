package Model;

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
public class CommunicationSocketThread extends Thread{

    private Socket socket;

    public CommunicationSocketThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Message msg = (Message) input.readObject();
            
            if (msg.getType() == MessageType.KEEPALIVE){
                output.writeObject(new Message(MessageType.ALIVE, ""));
                output.flush();
            }else if (msg.getType() == MessageType.USERMESSAGE){
                
            }
            
            output.close();
            input.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicationSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }     
    
}
