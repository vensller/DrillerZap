package DAO;

import java.util.List;

/**
 *
 * @author Ivens
 */
public class UserDao implements Dao{
    
    private String sqlInsert = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, TELEPHONE) VALUES(?,?,?,?);";
    private String sqlUpdate = "UPDATE USERS A SET A.NAME = ?, A.EMAIL = ?, A.PASSWORD = ?, A.TELEPHONE = ? WHERE A.ID = ?;";
    private String sqlDelete = "DELETE USERS WHERE ID = ?;";
    private String sqlFind = "SELECT * FROM USERS WHERE ID = ?;";
    private String sqlFindEmail = "SELECT * FROM USERS WHERE EMAIL = ?;";

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
        return null;
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
