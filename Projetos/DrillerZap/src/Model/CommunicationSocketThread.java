package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class CommunicationSocketThread extends Thread {

    private Socket socket;
    private CommunicationSocket communication;

    public CommunicationSocketThread(Socket socket, CommunicationSocket communication) {
        this.socket = socket;
        this.communication = communication;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = (Message) input.readObject();

            switch (msg.getType()) {
                case KEEPALIVE:
                    output.writeObject(new Message(MessageType.ALIVE, ""));
                    output.flush();
                    break;
                case USERMESSAGE:
                    MessageModel message = (MessageModel) msg.getMessage();
                    communication.messageReceived(message);
                    break;
                case CONTACTNOTALIVE:
                    UserConfig contact = (UserConfig) msg.getMessage();

                    for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()) {
                        if (user.getUser().getEmail().equals(contact.getUser().getEmail())) {
                            user.setIsLogged(false);
                            user.setIp("");
                            user.setPort(0);
                        }
                    }

                    break;
                case CONTACTALIVE:
                    UserConfig contactAlive = (UserConfig) msg.getMessage();

                    for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()) {
                        if (user.getUser().getEmail().equals(contactAlive.getUser().getEmail())) {
                            user.setIsLogged(true);
                            user.setIp(contactAlive.getIp());
                            user.setPort(contactAlive.getPort());
                        }
                    }
                    break;
                case RELOADCONTACTS:
                    output = new ObjectOutputStream(socket.getOutputStream());
                    input = new ObjectInputStream(socket.getInputStream());

                    output.writeObject(new Message(MessageType.GIVECONTACTS, Configuration.getInstance().getLoggedUser().getUser()));
                    output.flush();
                    Message inpMsg = (Message) input.readObject();
                    ArrayList<UserConfig> listContacts = (ArrayList<UserConfig>) inpMsg.getMessage();
                    Configuration.getInstance().getLoggedUser().getUser().setContacts(listContacts);
                    communication.reloadContats();
                    break;
                default:
                    break;
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
