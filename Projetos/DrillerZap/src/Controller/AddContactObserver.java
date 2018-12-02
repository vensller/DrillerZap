package Controller;

/**
 *
 * @author Paulo
 */
public interface AddContactObserver {
    void addContactApproved();
    void addContactNotApproved(String error);
    
}
