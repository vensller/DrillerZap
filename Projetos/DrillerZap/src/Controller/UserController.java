package Controller;

import Model.User;
import Model.Configuration;
import Model.Message;
import Model.MessageType;
import Model.UserConfig;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
public class UserController {

    private List<ApprovedObserver> observers;
    private List<LoginObserver> loginObservers;
    private List<UpdateObserver> updateObserves;

    public UserController() {
        observers = new ArrayList<>();
        loginObservers = new ArrayList<>();
        updateObserves = new ArrayList<>();

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

                loginApproved();
                
                Configuration.getInstance().setLoggedUser((UserConfig) msgApproved.getMessage());

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

}
