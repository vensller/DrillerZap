package Model;

/**
 *
 * @author Paulo
 */
public class Configuration {
    
    private String address;
    private int port;
    private UserConfig loggedUser;

    private static Configuration instance;

    private Configuration() {
    }

    public synchronized static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public UserConfig getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserConfig loggedUser) {
        this.loggedUser = loggedUser;
    }
}
