package Model;

/**
 *
 * @author Ivens
 */
public class UserConfig {
    
    private User user;
    private String ip;
    private int port;

    public UserConfig(User user, String ip, int port) {
        this.user = user;
        this.ip = ip;
        this.port = port;
    }

    public User getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }   
    
}