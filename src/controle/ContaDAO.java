package controle;

import java.util.List;
import modelo.Conta;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ContaDAO {
    
    
    public static Conta consultaConta(Integer numConta){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(Conta.class);
        crit.add(Restrictions.eq("numConta",numConta));
        List<Conta> lista = crit.list();
        Conta c = lista.get(0);
        return c;
    }
    public static Conta ultimaConta(){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(Conta.class);
        crit.addOrder(Order.desc("numConta"));
        List<Conta> lista = crit.list();
        Conta c = lista.get(0);
        return c;
    }
    
}
