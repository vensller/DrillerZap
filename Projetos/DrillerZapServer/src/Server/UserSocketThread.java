package Server;

import DAO.ContactDao;
import DAO.UserDao;
import Model.Message;
import Model.MessageType;
import Model.ServerConfig;
import Model.User;
import Model.UserConfig;
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
public class UserSocketThread extends Thread{
    
    private Socket socket;
    private UserDao userDao;
    private ContactDao contactDao;

    public UserSocketThread(Socket socket) {
        this.socket = socket;
        this.userDao = new UserDao();
        this.contactDao = new ContactDao();
        System.out.println("Client " + socket.getInetAddress().toString() + " connected.");
    }    

    @Override
    public void run(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());            
            
            Object obj =  input.readObject();            
            
            if (obj instanceof Message){
                Message msg = (Message) obj;
                Message outMsg = processMessage(msg);
                
                if (outMsg != null){
                    output.writeObject(outMsg);
                    output.flush();
                }
                    
            }           
                                    
            output.close();            
            input.close();        
            System.out.println("Client " + socket.getInetAddress().toString() + " finished.");
            socket.close();    
        }catch(IOException e){
            e.printStackTrace();            
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();            
        }         
    }  
    
    private Message processMessage(Message msg){        
        User user = (User) msg.getMessage();
        switch (msg.getType()){
            case ALIVE :
                
                break;
            case DOLOGIN :
                return getLoginMessage(user);
            case DOLOGOFF :
                return getLogoffMessage(user);
            case GIVECONTACTS :                
                return getUserContactsMessage(user);
            case REGISTER :
                return getRegisterMessage(user);
            default : System.out.println("WRONG MESSAGE TYPE RECEIVED! " + msg.toString());
        }
        
        return null;
    }
    
    private Message getRegisterMessage(User user){
        if (userDao.getObjByUnique(user.getEmail()) == null){
            userDao.insert(user);
            return new Message(MessageType.REGISTERAPROVED, null);
        }else return new Message(MessageType.REGISTERNOTAPROVED, "Email já cadastrado!");
    }
    
    private Message getLoginMessage(User user){        
        User dbUser = (User) userDao.getObjByUnique(user.getEmail());
        
        if (dbUser != null){
            UserConfig userCfg = new UserConfig(dbUser, socket.getRemoteSocketAddress().toString(), socket.getPort());
            ServerConfig.getInstance().addUser(userCfg);
            return new Message(MessageType.USERLOGGED, userCfg);
        }else return new Message(MessageType.USERNOTLOGGED, "Email não está cadastrado!");                
    }
    
    private Message getLogoffMessage(User user){
        ServerConfig.getInstance().removeUser(user);
        return new Message(MessageType.USERLOGGEDOFF, "Usuário deslogado com sucesso!");
    }
    
    private Message getUserContactsMessage(User user){        
        List<UserConfig> userContacts = new ArrayList<>();
        List<Object> contacts = contactDao.getObjects(user.getID() + "");
        
        for (Object obj : contacts){
            UserConfig userCfg = ServerConfig.getInstance().getLoggedUserConfig((User) obj);
            
            if (userCfg != null){
                userContacts.add(userCfg);
            }
        }
        
        return new Message(MessageType.SENDCONTACTS, userContacts);
    }
    
    
}
