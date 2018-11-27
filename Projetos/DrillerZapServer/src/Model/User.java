package Model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ivens
 */
public class User implements Serializable{
    
    private static final long serialVersionUID = 7526472295622776147L;
    private int ID;
    private String name;
    private String email;
    private String telephone;
    private String password;
    private List<User> contacts;

    public User(int ID, String name, String email, String telephone, String password, List<User> contacts) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.contacts = contacts;
    }

    public User() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }
    
    


    
}
