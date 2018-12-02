package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicationSocket extends Thread{

    @Override
    public void run() {        
        try {
            ServerSocket serverSocket = new ServerSocket(56001);
            serverSocket.setReuseAddress(true);
            while (true) {
                Socket socket = serverSocket.accept();
                CommunicationSocketThread thread = new CommunicationSocketThread(socket);
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
}
