package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class ChatMessages {
    
    private UserConfig contact;
    private ListMessages firstMessage;

    public ChatMessages(UserConfig contact, ListMessages messages) {
        this.contact = contact;
        this.firstMessage = messages;
    }        

    public UserConfig getContact() {
        return contact;
    }

    public ListMessages getMessages() {
        return firstMessage;
    }

    public List<String> getListMessages() {
        List<String> listMessages = new ArrayList<>();
        
        ListMessages msg = firstMessage;
        
        while (msg != null){
            listMessages.add(msg.getOwner().getName() + " said: " + msg.getMessage());
            msg = msg.getNext();
        }
        
        return listMessages;
    }
    
}
