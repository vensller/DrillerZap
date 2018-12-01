package DAO;

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
    private UserDao userDao;
    
    public ContactDao(){
        userDao = new UserDao();
    }

    @Override
    public void insert(Object obj) {
        
    }

    @Override
    public void delete(Object obj) {

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
