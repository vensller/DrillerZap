package Model;

import java.io.Serializable;

/**
 *
 * @author Ivens
 */
public class Contact implements Serializable{
    private static final long serialVersionUID = 7526472295622776127L;
    
    private User user;
    private User contact;
    
    public Contact(){
        
    }

    public Contact(User user, User contact) {
        this.user = user;
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }   
    
}
