package Main;

import Model.ServerConfig;
import Server.Server;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivens
 */
public class Main {
    
    public static void main(String[] args) {
        //"jdbc:postgresql://localhost/DrillerZap", "postgres", "idmf7798"
        ServerConfig.getInstance().setDatabase(JOptionPane.showInputDialog("Digite o caminho da base de dados"));
        ServerConfig.getInstance().setUsername(JOptionPane.showInputDialog("Digite o usuário da base de dados"));
        ServerConfig.getInstance().setPassword(JOptionPane.showInputDialog("Digite a senha da base de dados"));
        Server server = new Server();
        server.execute();
    }
    
}
