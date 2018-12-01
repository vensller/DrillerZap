package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class Server {

    public void execute(){
        try {
            ServerSocket serverSocket = new ServerSocket(56000);
            serverSocket.setReuseAddress(true);
            KeepAliveThread keepAlive = new KeepAliveThread();
//            keepAlive.start();
            while (true){
                System.out.println("Waiting connection...");
                Socket socket = serverSocket.accept();
                UserSocketThread thread = new UserSocketThread(socket);
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}
