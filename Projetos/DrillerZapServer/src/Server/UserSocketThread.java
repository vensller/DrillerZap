package Server;

import java.net.Socket;

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
    public void run() {
        
    }  
    
    
}
