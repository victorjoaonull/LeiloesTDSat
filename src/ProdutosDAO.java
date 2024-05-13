import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    public List<ProdutosDTO> listarProdutos() throws SQLException{
        
        conectaDAO conecta = new conectaDAO();
        conecta.conectar();

        List lista = new ArrayList();
            
            String sql = "SELECT*FROM produtos;";
            
            
            PreparedStatement query = conecta.getConexao().prepareStatement(sql);
            
            ResultSet recebe = query.executeQuery();
            
                    while(recebe.next()){
                        ProdutosDTO p = new ProdutosDTO();
                        p.setId(recebe.getInt("id"));
                        p.setNome(recebe.getString("nome"));
                        p.setStatus(recebe.getString("status"));
                        p.setValor(recebe.getInt("valor"));
                        lista.add(p);
                        
                        
                    }
            
            conecta.desconectar();
            return lista;
}

    public boolean cadastrar (ProdutosDTO p) {
        try{
            conectaDAO conecta = new conectaDAO();
            conecta.conectar();
            
            String sql = "INSERT INTO produtos (nome,valor,status) values (?,?,?);";
            
            PreparedStatement query = conecta.getConexao().prepareStatement(sql);
            
            query.setString(1, p.getNome());
            query.setInt(2, p.getValor());
            query.setString(3, p.getStatus());
            
            query.execute();
            
            conecta.desconectar();
            JOptionPane.showMessageDialog(null, "Produto cadastrado!");
            return true;
        } catch (SQLException se){
            JOptionPane.showMessageDialog(null, "Erro ao registrar os valores");
            return false;
        }
    }
    public boolean venderProduto (int id) {
        try{
            conectaDAO conecta = new conectaDAO();
            conecta.conectar();
            
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id=?;";
            
            PreparedStatement query = conecta.getConexao().prepareStatement(sql);
            
            query.setInt(1, id);
            
            query.execute();
            
            conecta.desconectar();
            JOptionPane.showMessageDialog(null, "Produto vendido!");
            return true;
        } catch (SQLException se){
            JOptionPane.showMessageDialog(null, "Erro ao registrar os valores");
            return false;
        }
    }
}

