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
            stmt.setString(1, whereCondition);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                User user = (User) userDao.getObjById(rs.getString("id_contact"));
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
        return null;
    }
    
}
