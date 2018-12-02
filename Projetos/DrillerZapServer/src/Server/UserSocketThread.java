package Server;

import DAO.ContactDao;
import DAO.UserDao;
import Model.Contact;
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
        switch (msg.getType()){
            case ALIVE :                
                break;
            case DOLOGIN :
                return getLoginMessage(msg);
            case DOLOGOFF :
                return getLogoffMessage(msg);
            case GIVECONTACTS :                
                return getUserContactsMessage(msg);
            case REGISTER :
                return getRegisterMessage(msg);
            case UPDATEUSER :
                return getUserUpdateMessage(msg);
            case REGISTERCONTACT :
                return getRegisterContactMessage(msg);
            case REMOVECONTACT :
                return getRemoveContactMessage(msg);
            default : System.out.println("WRONG MESSAGE TYPE RECEIVED! " + msg.toString());
        }
        
        return null;
    }
    
    private Message getRemoveContactMessage(Message message){
        Contact contact = (Contact) message.getMessage();
        User user = (User) userDao.getObjByUnique(contact.getUser().getEmail());
        User userContact = (User) userDao.getObjByUnique(contact.getContact().getEmail());
        contact.setUser(user);
        contact.setContact(userContact);
        contactDao.delete(contact);
        return new Message(MessageType.REMOVECONTACTSUCESS, "Contato removido com sucesso!");
    }
    
    private Message getRegisterContactMessage(Message message){
        Contact contact = (Contact) message.getMessage();
        User user = (User) userDao.getObjByUnique(contact.getUser().getEmail());
        User userContact = (User) userDao.getObjByUnique(contact.getContact().getEmail());
        contact.setContact(userContact);
        contact.setUser(user);
        
        if (user != null && userContact != null){
            if (contactDao.getObjByUnique(user.getID() + " AND A.ID_CONTACT = " + userContact.getID()) == null){
                contactDao.insert(contact);
                return new Message(MessageType.CONTACTREGISTERED, "Contato adicionado com sucesso!");
            }else return new Message(MessageType.CONTACTREGFAIL, "Contato já adicionado!");
        }else return new Message(MessageType.CONTACTREGFAIL, "Contato não encontrado!");        
    }
    
    private Message getRegisterMessage(Message message){
        User user = (User) message.getMessage();
        if (userDao.getObjByUnique(user.getEmail()) == null){
            userDao.insert(user);
            return new Message(MessageType.REGISTERAPROVED, null);
        }else return new Message(MessageType.REGISTERNOTAPROVED, "Email já cadastrado!");
    }
    
    private Message getLoginMessage(Message message){
        User user = (User) message.getMessage();
        User dbUser = (User) userDao.getObjByUnique(user.getEmail());;
        
        if (dbUser != null){
            if (dbUser.getPassword().trim().equals(user.getPassword().trim())){
                UserConfig userCfg = new UserConfig(dbUser, socket.getInetAddress().toString(), socket.getPort(), true);
                Message msg = getUserContactsMessage(new Message(MessageType.GIVECONTACTS, userCfg.getUser()));
                userCfg.getUser().setContacts((ArrayList<UserConfig>) msg.getMessage());
                ServerConfig.getInstance().addUser(userCfg);
                return new Message(MessageType.USERLOGGED, userCfg);
            }else return new Message(MessageType.USERNOTLOGGED, "Senha não confere!");
        }else return new Message(MessageType.USERNOTLOGGED, "Email não está cadastrado!");                
    }
    
    private Message getLogoffMessage(Message message){
        User user = (User) message.getMessage();
        ServerConfig.getInstance().removeUser(user);
        return new Message(MessageType.USERLOGGEDOFF, "Usuário deslogado com sucesso!");
    }
    
    public Message getUserContactsMessage(Message message){        
        User user = (User) message.getMessage();
        List<UserConfig> userContacts = new ArrayList<>();
        List<Object> contacts = contactDao.getObjects(user.getID() + "");
        
        for (Object obj : contacts){
            UserConfig userCfg = ServerConfig.getInstance().getLoggedUserConfig((User) obj);
            
            if (userCfg != null){
                userContacts.add(userCfg);
            }else userContacts.add(new UserConfig((User) obj, "", 0, false));
        }
        
        return new Message(MessageType.SENDCONTACTS, userContacts);
    }
    
    private Message getUserUpdateMessage(Message message){
        User user = (User) message.getMessage();
        User dbUser = (User) userDao.getObjByUnique(user.getEmail()); 
        user.setID(dbUser.getID());
        if (dbUser != null){
            userDao.update(user);
            return new Message(MessageType.UPDATESUCESS, "");
        }
        return new Message(MessageType.UPDATEFAIL, "Usuário não encontrado!");
    }
    
    
}
