package DAO;

import Model.Contact;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class ContactDao implements Dao{
    
    private String sqlFindContacts = "SELECT * FROM CONTACT A WHERE A.ID_USER = ?";
    private String sqlInsert = "INSERT INTO CONTACT (ID_USER, ID_CONTACT) VALUES (?, ?)";
    private String sqlDelete = "DELETE CONTACT WHERE ID_USER = ? AND ID_CONTACT = ?";
    private String sqlFindUnique = "SELECT * FROM CONTACT A WHERE A.ID_USER = ";
    private UserDao userDao;
    
    public ContactDao(){
        userDao = new UserDao();
    }

    @Override
    public void insert(Object obj) {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(true);
            Contact contact = (Contact) obj;
            PreparedStatement stmt = con.prepareStatement(sqlInsert);
            stmt.setInt(1, contact.getUser().getID());
            stmt.setInt(2, contact.getContact().getID());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(Object obj) {
        Connection con = ConnectionFactory.getConnection();
        try {
            con.setAutoCommit(true);
            Contact contact = (Contact) obj;                    
            PreparedStatement stmt = con.prepareStatement(sqlDelete);
            stmt.setInt(1, contact.getUser().getID());
            stmt.setInt(2, contact.getContact().getID());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public List<Object> getObjects(String whereCondition) {
        List<Object> contacts = new ArrayList<>();
        
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlFindContacts);
            stmt.setInt(1, Integer.parseInt(whereCondition));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user = (User) userDao.getObjById(rs.getInt("id_contact") +"");
                if (user != null){
                    contacts.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return contacts;
    }

    @Override
    public Object getObjById(String id) {
        return null;
    }

    @Override
    public Object getObjByUnique(String whereUnique) {
        Contact contact = null;
        
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlFindUnique + whereUnique);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                contact = new Contact();
                contact.setUser((User) userDao.getObjById(rs.getInt("id_user") +""));
                contact.setContact((User) userDao.getObjById(rs.getInt("id_contact") +""));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return contact;
    }
    
}
