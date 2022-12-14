
package DAO;

/**
 *
 * @author Ricardo
 */
import Modelo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CidadeDAO {
    public static int ProximoCodigo(){
        Connection conexao = FabricaConexao.getConnection();
        
        Statement consulta = null;
        ResultSet resultado = null;
        int codigo = -1;
        
        String sql = "select max(codigo) as CODIGO from CIDADE";
        
        try {
            consulta = (Statement)conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            resultado.next();
            codigo = resultado.getInt("CODIGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL de navegação: " + e.getMessage());
        } finally {
            try {
               consulta.close();
               conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função ProximoCodigo(): " + e.getMessage());
            }            
        }      
        
        return codigo + 1;
    }
    
    public static void Salvar(Cidade cidade){
        Connection conexao = FabricaConexao.getConnection();
        
        PreparedStatement insereSt = null;
        
        String sql = "insert cidade (CODIGO, NOME, ESTADO, CEP) values (?,?,?,?)";
        
        try {            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1, cidade.getCodigo());
            insereSt.setString(2, cidade.getNome());
            insereSt.setString(3, cidade.getEstado());
            insereSt.setString(4, cidade.getCep());
            insereSt.executeUpdate();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir cidade: " + e.getMessage());
        } finally {
            try {
                JOptionPane.showMessageDialog(null, "Cidade incluída com sucesso.");
                insereSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Salvar(): " + e.getMessage());
            }
        }            
    }
}
