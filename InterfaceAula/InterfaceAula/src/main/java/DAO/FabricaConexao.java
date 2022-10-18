
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class FabricaConexao {
    public static Connection getConnection(){
        Connection  conexao = null;
        try{
            String URL = "jdcb:mysql://10.140.10.33/Bd_teste";
            String usuario = "root";
            String senha = "";
            conexao = DriverManager.getConnection(URL,usuario,senha);
        }catch(SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
        return conexao;
    }
}
