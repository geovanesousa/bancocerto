package controle;

import java.util.List;
import modelo.Endereco;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class EnderecoDAO {
    
    public static Endereco consultaEnd(Integer idEndereco){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(Endereco.class);
        crit.add(Restrictions.eq("idEndereco",idEndereco));
        List<Endereco> lista = crit.list();
        Endereco e = lista.get(0);
        return e;
    }
}
