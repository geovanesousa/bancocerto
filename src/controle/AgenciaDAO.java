package controle;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Agencia;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class AgenciaDAO {
    
    public static Agencia inserirAg(Agencia a) throws Exception{
        
            GenericoDAO.inserir(a.getEndereco());
            GenericoDAO.inserir(a);
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            return a;
    }
    
    public static Agencia consultaAg(Integer codAgencia){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(Agencia.class);
        crit.add(Restrictions.eq("codAgencia",codAgencia));
        List<Agencia> lista = crit.list();
        Agencia a = lista.get(0);
        return a;
    }
    
    public static void excluirAg(Integer codAgencia)throws Exception{
        
        Agencia a = AgenciaDAO.consultaAg(codAgencia);
            GenericoDAO.excluir(a);
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
    }
    public static Agencia ultimaAg(){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(Agencia.class);
        crit.addOrder(Order.desc("codAgencia"));
        List<Agencia> lista = crit.list();
        Agencia a = lista.get(0);
        return a;
    }
    
}
