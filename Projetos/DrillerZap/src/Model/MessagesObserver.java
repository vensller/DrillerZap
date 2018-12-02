package Model;

/**
 *
 * @author Ivens
 */
public interface MessagesObserver {
    
    void messageReceived(String contactEmail, String message);
    void messageSend(String contactEmail, String message);
    
}
