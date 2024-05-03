
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    Connection conexao;
    
    public Connection getConexao(){
        return conexao;
    }
    
    public void conectar() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/uc11","root","1234");
            System.out.println("SUCESSO NA CONEXÃO");
        } catch (ClassNotFoundException e) {
            System.out.println("FALHA");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados");
        }
    }
    
    public void desconectar(){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
                System.out.println("BANCO DESCONECTADO");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar ao banco de dados");
        }
    }
}
