package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class UserDao implements Dao{
    
    private String sqlInsert = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, TELEPHONE) VALUES(?,?,?,?)";
    private String sqlUpdate = "UPDATE USERS A SET A.NAME = ?, A.PASSWORD = ?, A.TELEPHONE = ? WHERE A.ID = ?";
    private String sqlDelete = "DELETE USERS WHERE ID = ?";
    private String sqlFind = "SELECT * FROM USERS WHERE ID = ?";
    private String sqlFindEmail = "SELECT * FROM USERS WHERE EMAIL LIKE ?";

    @Override
    public void insert(Object obj) {
        Connection con = ConnectionFactory.getConnection();
        User user = (User) obj;
        try {
            con.setAutoCommit(true);
            PreparedStatement stmt = con.prepareStatement(sqlInsert);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getTelephone());
            stmt.execute();            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {
        Connection con = ConnectionFactory.getConnection();
        User user = (User) obj;
        try {
            con.setAutoCommit(true);
            PreparedStatement stmt = con.prepareStatement(sqlUpdate);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getTelephone());
            stmt.setInt(4, user.getID());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<Object> getObjects(String whereCondition) {
        return null;
    }

    @Override
    public Object getObjById(String id) {
         User user = null;
        
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlFind);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user.setID(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setTelephone(rs.getString("telephone"));
                user.setPassword(rs.getString("password"));                
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
        
        return user;
    }

    @Override
    public Object getObjByUnique(String whereUnique) {
        User user = null;
        
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(sqlFindEmail);
            stmt.setString(1, whereUnique);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                user = new User();
                user.setID(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setTelephone(rs.getString("telephone"));
                user.setPassword(rs.getString("password"));                
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
        
        return user;
    }
    
    
    
    
}
