package Controller;

/**
 *
 * @author Paulo
 */
public interface AddContactObserver {
    void addContactApproved();
    void addContactNotApproved(String error);
    void receiveContact(String email);
    void removeContact(String email);
    void contactAlive(String email, boolean alive);
}
