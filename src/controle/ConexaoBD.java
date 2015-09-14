package controle;


import org.hibernate.Transaction;
import org.hibernate.Session;
import util.HibernateUtil;

public class ConexaoBD {
    
    private static Session s;
    private static Transaction tr;
    
    public static Session abrirConexaoBD(){
        
        ConexaoBD.s = HibernateUtil.getSessionFactory().openSession();
        ConexaoBD.tr = s.beginTransaction();
        
        return ConexaoBD.s;
        
    } 
    
    public static void fecharConexao(){
        
        ConexaoBD.s.close();
        
    }

    public static Session getSessao() {
        return ConexaoBD.s;
    }

    public static Transaction getTr() {
        return tr;
    }
    
    
    
    
    
}
