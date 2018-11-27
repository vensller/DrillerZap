package Controller;

import Model.User;
import Model.Configuration;
import Model.Message;
import Model.MessageType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
public class UserController {

    public void submitUserToServer(String name, String email, String telephone, String password){        
        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            Socket socket = new Socket(Configuration.getInstance().getAddress(), Configuration.getInstance().getPort());
            socket.setReuseAddress(true);
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Message msg = new Message(MessageType.REGISTER, user);
            
            output.writeObject(msg);
            
            output.flush();
            output.close();            
            input.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
