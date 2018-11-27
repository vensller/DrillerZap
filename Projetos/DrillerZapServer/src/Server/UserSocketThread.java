package Server;

import Model.Message;
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
public class UserSocketThread extends Thread{
    
    private Socket socket;

    public UserSocketThread(Socket socket) {
        this.socket = socket;
    }    

    @Override
    public void run(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());            
            
            Object obj =  input.readObject();            
            
            if (obj instanceof Message){
                Message msg = (Message) obj;
                Message outMsg = processMessage(msg);
                output.writeObject(outMsg);
            }           
                                    
            output.close();            
            input.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
            try{
                socket.close();
            }catch(IOException e2){
                e2.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(UserSocketThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }         
    }  
    
    private Message processMessage(Message msg){
        return null;
    }
    
    
}
