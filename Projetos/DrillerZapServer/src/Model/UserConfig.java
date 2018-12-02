package Model;

import java.io.Serializable;

/**
 *
 * @author Ivens
 */
public class UserConfig implements Serializable{
    private static final long serialVersionUID = 75264722956227547L;
    
    private User user;
    private String ip;
    private int port;
    private boolean isLogged;

    public UserConfig(User user, String ip, int port, boolean isLogged) {
        this.user = user;
        this.ip = ip;
        this.port = port;
        this.isLogged = isLogged;
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

    public boolean isLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
