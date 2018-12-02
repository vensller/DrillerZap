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
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeepAliveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<UserConfig> list = new ArrayList<>();
            list.addAll(ServerConfig.getInstance().getLoggedUsers());
            List<CommunicateUserAlive> threads = new ArrayList<>();
            
            for (UserConfig user : list){
                CommunicateUserAlive thread = new CommunicateUserAlive(user);                
                thread.start();
                threads.add(thread);
            }
            
            List<UserConfig> notAlive = new ArrayList<>();
            
            for (CommunicateUserAlive a : threads){
                try {
                    a.join();
                    if (!a.getUserAlive()){
                        notAlive.add(a.getUser());
                    }                        
                } catch (InterruptedException ex) {
                    Logger.getLogger(KeepAliveThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            for (UserConfig user : notAlive){
                CommunicateContacts thread = new CommunicateContacts(user);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(KeepAliveThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    }  
    
    
}
