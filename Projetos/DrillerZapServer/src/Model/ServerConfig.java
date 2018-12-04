package Model;

import java.util.ArrayList;

/**
 *
 * @author Ivens
 */
public class ServerConfig {
    
    private static ServerConfig instance;
    private ArrayList<UserConfig> loggedUsers;
    private String database;
    private String username;
    private String password;
    
    private ServerConfig(){
        loggedUsers = new ArrayList<>();
    }
    
    private synchronized ArrayList<UserConfig> getUsers(){
        return (ArrayList<UserConfig>) loggedUsers.clone();
    }
    
    public static synchronized ServerConfig getInstance(){
        if (instance == null){
            instance = new ServerConfig();
        }
        
        return instance;
    }

    public synchronized UserConfig getLoggedUserConfig(User user) {        
        ArrayList<UserConfig> list = getUsers();
        for (UserConfig userConfig : list){
            if (userConfig.getUser().getEmail().equals(user.getEmail())){
                return userConfig;
            }
        }
        
        return null;
    }
    
    public synchronized void addUser(UserConfig user){
        loggedUsers.add(user);
    }
    
    public synchronized void removeUser(User user){
        ArrayList<UserConfig> list = getUsers();
        for (UserConfig userCfg : list){
            if (userCfg.getUser().getEmail().equals(user.getEmail())){
                loggedUsers.remove(userCfg);
                return;
            }
        }
    }    

    public synchronized ArrayList<UserConfig> getLoggedUsers() {
        return getUsers();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
