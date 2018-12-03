package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicationSocket extends Thread{
    
    private List<MessagesObserver> messagesObserverList;

    public CommunicationSocket() {
        messagesObserverList = new ArrayList<>();
    }
    
    public void observ(MessagesObserver obs){
        messagesObserverList.add(obs);
    }

    @Override
    public void run() {        
        try {
            ServerSocket serverSocket = new ServerSocket(56001);
            serverSocket.setReuseAddress(true);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client " + socket.getInetAddress().toString() + " connected.");
                CommunicationSocketThread thread = new CommunicationSocketThread(socket, messagesObserverList);
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    
}
