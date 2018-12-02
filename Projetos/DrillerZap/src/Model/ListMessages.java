package Model;

/**
 *
 * @author Ivens
 */
public class ListMessages {
    
    private User owner;
    private String message;
    private ListMessages next;

    public ListMessages(User owner, String message, ListMessages next) {
        this.owner = owner;
        this.message = message;
        this.next = next;
    }

    public User getOwner() {
        return owner;
    }

    public String getMessage() {
        return message;
    }

    public ListMessages getNext() {
        return next;
    }  
    
    
}
