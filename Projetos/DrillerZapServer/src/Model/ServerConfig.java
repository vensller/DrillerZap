package Model;

import java.util.ArrayList;

/**
 *
 * @author Ivens
 */
public class ServerConfig {
    
    private static ServerConfig instance;
    private ArrayList<UserConfig> loggedUsers;
    
    private ServerConfig(){
        loggedUsers = new ArrayList<>();
    }
    
    public static synchronized ServerConfig getInstance(){
        if (instance == null){
            instance = new ServerConfig();
        }
        
        return instance;
    }

    public ArrayList<UserConfig> getLoggedUsers() {
        return loggedUsers;
    }

    public synchronized void addUser(UserConfig user){
        loggedUsers.add(user);
    }
    
    public synchronized void removeUser(UserConfig user){
        loggedUsers.remove(user);
    }
    
}
