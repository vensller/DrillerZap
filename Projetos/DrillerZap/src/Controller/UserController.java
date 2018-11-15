/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Paulo
 */
public class UserController {

    public void submitUsertoServer(String ip, int port, User user) throws IOException {
        Socket socket = new Socket(ip, port);
        socket.setReuseAddress(true);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        output.writeUTF("submitUser");
        output.writeObject(user);
        output.flush();
        output.close();            
        input.close();
        socket.close();

    }

}
