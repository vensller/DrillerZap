package Model;

/**
 *
 * @author Ivens
 */
public class MessageModel {
    
    private User from;
    private User to;
    private String message;

    public MessageModel(User from, User to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
