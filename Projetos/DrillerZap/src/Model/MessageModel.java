package Model;

import java.io.Serializable;

/**
 *
 * @author Ivens
 */
public class MessageModel implements Serializable {
    
    private UserConfig from;
    private UserConfig to;
    private String message;


    public MessageModel(UserConfig from, UserConfig to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;

    }

    public UserConfig getFrom() {
        return from;
    }

    public void setFrom(UserConfig from) {
        this.from = from;
    }

    public UserConfig getTo() {
        return to;
    }

    public void setTo(UserConfig to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
