package controle;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class GenericoDAO {
    
    public static void inserir(Object objeto) throws Exception{
        
        ConexaoBD.abrirConexaoBD();
        ConexaoBD.getSessao().save(objeto);
        ConexaoBD.getTr().commit();
        ConexaoBD.fecharConexao();
        
    }
    
    public static void atualizar(Object objeto) throws Exception{
        
        ConexaoBD.abrirConexaoBD();
        ConexaoBD.getSessao().update(objeto);
        ConexaoBD.getTr().commit();
        ConexaoBD.fecharConexao();
        
    }
    
    public static void excluir(Object objeto) throws Exception{
        
        ConexaoBD.abrirConexaoBD();
        ConexaoBD.getSessao().delete(objeto);
        ConexaoBD.getTr().commit();
        ConexaoBD.fecharConexao();
        
    }
    
    
    public static List consultar(Class classe, String coluna, String dado){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(classe);
        crit.add(Restrictions.eq(coluna,dado));
        List lista = crit.list();
        return lista;
        
    }
    public static List listarTodos(Class classe){
        
        ConexaoBD.abrirConexaoBD();
        Criteria crit = ConexaoBD.getSessao().createCriteria(classe);
        List lista = crit.list();
        return lista;
        
    }
    
    
    
}
