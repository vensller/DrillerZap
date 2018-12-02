package Controller;

import Model.ChatMessages;
import Model.CommunicationSocket;
import Model.User;
import Model.Configuration;
import Model.Contact;
import Model.ListMessages;
import Model.Message;
import Model.MessageType;
import Model.UserConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
public class UserController{

    private List<ApprovedObserver> observers;
    private List<LoginObserver> loginObservers;
    private List<UpdateObserver> updateObserves;
    private List<AddContactObserver> addContactObservers;
    private List<ChatMessages> chatMessages;

    public UserController() {
        observers = new ArrayList<>();
        loginObservers = new ArrayList<>();
        updateObserves = new ArrayList<>();
        addContactObservers = new ArrayList<>();
        chatMessages = new ArrayList<>();
    }

    public void submitUserToServer(String name, String email, String telephone, String password) throws ClassNotFoundException {
        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setTelephone(telephone);
            user.setPassword(password);
            Socket socket = new Socket(Configuration.getInstance().getAddress(), Configuration.getInstance().getPort());
            socket.setReuseAddress(true);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = new Message(MessageType.REGISTER, user);

            output.writeObject(msg);
            output.flush();

            Object obj = input.readObject();
            Message msgApproved = (Message) obj;

            if (msgApproved.getType() == MessageType.REGISTERAPROVED) {

                cadastroApproved(msg);

            } else {
                cadastroNoApproved(msg.getMessage().toString());
            }

            output.close();
            input.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cadastroApproved(Message msg) {
        for (ApprovedObserver obs : observers) {
            obs.cadastroApproved();
        }

    }

    public void cadastroNoApproved(String reason) {
        for (ApprovedObserver obs : observers) {
            obs.cadastroNoApproved(reason);
        }

    }

    public void observ(ApprovedObserver obs) {
        observers.add(obs);
    }

    public void login(String email, String password) throws ClassNotFoundException {
        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            Socket socket = new Socket(Configuration.getInstance().getAddress(), Configuration.getInstance().getPort());
            socket.setReuseAddress(true);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = new Message(MessageType.DOLOGIN, user);

            output.writeObject(msg);
            output.flush();

            Object obj = input.readObject();
            Message msgApproved = (Message) obj;

            if (msgApproved.getType() == MessageType.USERLOGGED) {
                Configuration.getInstance().setLoggedUser((UserConfig) msgApproved.getMessage());
                CommunicationSocket socketThread = new CommunicationSocket();
                socketThread.start();
                loginApproved();                           
            } else {
                loginNoApproved();
            }

            output.close();
            input.close();
            socket.close();
        } catch (ConnectException a) {
            loginNoApproved();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loginApproved() {
        for (LoginObserver obs : loginObservers) {
            obs.loginApproved();
        }

    }

    public void loginNoApproved() {
        for (LoginObserver obs : loginObservers) {
            obs.loginNotApproved();
        }

    }

    public void observLogin(LoginObserver obs) {
        loginObservers.add(obs);
    }

    public void updateUser(String email, String password, String telephone, String name) throws ClassNotFoundException {
        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setName(name);
            Socket socket = new Socket(Configuration.getInstance().getAddress(), Configuration.getInstance().getPort());
            socket.setReuseAddress(true);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = new Message(MessageType.UPDATEUSER, user);

            output.writeObject(msg);
            output.flush();

            Object obj = input.readObject();
            Message msgApproved = (Message) obj;

            if (msgApproved.getType() == MessageType.UPDATESUCESS) {

                updateApproved();

            } else {
                updateNoApproved();
            }

            output.close();
            input.close();
            socket.close();
        } catch (ConnectException a) {
            loginNoApproved();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateApproved() {
        for (UpdateObserver obs : updateObserves) {
            obs.updateApproved();
        }

    }

    public void updateNoApproved() {
        for (UpdateObserver obs : updateObserves) {
            obs.updateNotApproved();
        }

    }

    public void observUpdate(UpdateObserver obs) {
        updateObserves.add(obs);
    }
    
    public void addContact(String email) throws ClassNotFoundException {
        try {
            
            User user = new User();
            user.setEmail(email);
            Contact contact = new Contact(Configuration.getInstance().getLoggedUser().getUser(), user);
            Socket socket = new Socket(Configuration.getInstance().getAddress(), Configuration.getInstance().getPort());
            socket.setReuseAddress(true);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = new Message(MessageType.REGISTERCONTACT, contact);

            output.writeObject(msg);
            output.flush();

            Object obj = input.readObject();
            Message msgApproved = (Message) obj;

            output.close();
            input.close();
            
            if (msgApproved.getType() == MessageType.CONTACTREGISTERED) {
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());

                output.writeObject(new Message(MessageType.GIVECONTACTS, contact.getUser()));
                output.flush();
                ArrayList<UserConfig> listContacts = (ArrayList<UserConfig>) input.readObject();
                Configuration.getInstance().getLoggedUser().getUser().setContacts(listContacts);
                addContactApproved();
                
                

            } else {
                addContactNoApproved((String) msgApproved.getMessage());
            }

            socket.close();
        } catch (ConnectException a) {
            loginNoApproved();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void addContactApproved() {
        for (AddContactObserver obs : addContactObservers) {
            obs.addContactApproved();
        }

    }

    public void addContactNoApproved(String error) {
        for (AddContactObserver obs : addContactObservers) {
            obs.addContactNotApproved(error);
        }

    }
    
    public void notifyAddContact(String email){
        for (AddContactObserver obs : addContactObservers){
            obs.receiveContact(email);
        }
    }
    
    public void notifyRemoveContact(String email){
        for (AddContactObserver obs : addContactObservers){
            obs.removeContact(email);
        }
    }
    
    public void notifyContactAlive(String email, List<String> messages){
        for (AddContactObserver obs : addContactObservers){
            obs.contactAlive(email, true, messages);
        }
    }
    
    public void notifyContactNotAlive(String email){
        for (AddContactObserver obs : addContactObservers){
            obs.contactAlive(email, false, null);
        }
    }

    public void observAddContact(AddContactObserver obs) {
        addContactObservers.add(obs);
    }
    
    public void processContacts(){
        for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()) {
            notifyAddContact(user.getUser().getEmail());
        }
        
    }

    public void processAliveContacts() {
        for (UserConfig user : Configuration.getInstance().getLoggedUser().getUser().getContacts()){
            if (user.isLogged()){
                ChatMessages contactMessages = new ChatMessages(user, new ListMessages(user.getUser(), "", null));
                notifyContactAlive(user.getUser().getEmail(), contactMessages.getListMessages());
            }
            else notifyContactNotAlive(user.getUser().getEmail());
        }
    }

}
