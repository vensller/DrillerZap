package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivens
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        try {            
            return DriverManager.getConnection("jdbc:postgresql://localhost/DrillerZap", "postgres", "idmf7798");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }    
}
