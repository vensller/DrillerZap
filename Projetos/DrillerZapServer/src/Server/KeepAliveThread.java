package Server;

import Model.ServerConfig;
import Model.UserConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class KeepAliveThread extends Thread{

    @Override
    public void run() {
        while (true){
            try {
                sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeepAliveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<UserConfig> list = new ArrayList<>();
            list.addAll(ServerConfig.getInstance().getLoggedUsers());
            
            for (UserConfig user : list){
                CommunicateUserAlive thread = new CommunicateUserAlive(user);
                thread.start();
            }
            
        }       
    }  
    
    
}
