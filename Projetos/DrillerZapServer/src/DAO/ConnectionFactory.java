package DAO;

import Model.ServerConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ivens
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        try {            
            return DriverManager.getConnection(ServerConfig.getInstance().getDatabase(), ServerConfig.getInstance().getUsername(), ServerConfig.getInstance().getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }    
}
