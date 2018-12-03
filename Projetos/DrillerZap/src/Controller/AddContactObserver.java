package Controller;

import Model.MessageModel;
import java.util.List;

/**
 *
 * @author Paulo
 */
public interface AddContactObserver {
    void addContactApproved();
    void addContactNotApproved(String error);
    void receiveContact(String email);
    void removeContact(String email);
    void contactAlive(String email, boolean alive, List<String> messages);
    void messageReceived(String emailContact, MessageModel message);
    
}
