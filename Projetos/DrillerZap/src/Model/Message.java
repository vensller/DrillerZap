package Model;

import java.io.Serializable;

/**
 *
 * @author Ivens
 */
public class Message implements Serializable{
    
    private MessageType type;
    private Object message;

    public Message(MessageType type, Object message) {
        this.type = type;
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }  
    
    
}
