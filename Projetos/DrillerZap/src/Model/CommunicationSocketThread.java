package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicationSocketThread extends Thread{

    private Socket socket;
    private List<MessagesObserver> messagesObserverList;

    public CommunicationSocketThread(Socket socket, List<MessagesObserver> messagesObserverList) {
        this.socket = socket;
        this.messagesObserverList = messagesObserverList;
    }
    
    @Override
    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            Message msg = (Message) input.readObject();
            
            switch (msg.getType()){
                case KEEPALIVE :
                    output.writeObject(new Message(MessageType.ALIVE, ""));
                    output.flush();
                    break;
                case USERMESSAGE :
                    MessageModel message = (MessageModel) msg.getMessage();                
                    for (MessagesObserver obs : messagesObserverList){
                        obs.messageReceived(message.getFrom().getUser().getEmail(), message);
                    }
                    break;
                case CONTACTNOTALIVE :
                    UserConfig contact = (UserConfig) msg.getMessage();
                    
                    for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()){
                        if (user.getUser().getEmail().equals(contact.getUser().getEmail())){
                            user.setIsLogged(false);
                            user.setIp("");
                            user.setPort(0);
                        }                            
                    }
                    
                    break;
                case CONTACTALIVE :
                    UserConfig contactAlive = (UserConfig) msg.getMessage();
                    
                    for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()){
                        if (user.getUser().getEmail().equals(contactAlive.getUser().getEmail())){
                            user.setIsLogged(true);
                            user.setIp(contactAlive.getIp());
                            user.setPort(contactAlive.getPort());
                        }
                    }
                default : break;
            }                    
            
            output.close();
            input.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CommunicationSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CommunicationSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }     
    
}
