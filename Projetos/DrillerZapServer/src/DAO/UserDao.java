package DAO;

/**
 *
 * @author Ivens
 */
public class UserDao {
    
    private String sqlInsert = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, TELEPHONE) VALUES(?,?,?,?);";
    private String sqlUpdate = "UPDATE USERS A SET A.NAME = ?, A.EMAIL = ?, A.PASSWORD = ?, A.TELEPHONE = ? WHERE A.ID = ?;";
    private String sqlDelete = "DELETE USERS WHERE ID = ?;";
    private String sqlFind = "SELECT * FROM USERS WHERE ID = ?;";
    private String sqlFindEmail = "SELECT * FROM USERS WHERE EMAIL = ?;";
    
    
}
