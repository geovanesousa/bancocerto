package controle;


import java.util.List;
import modelo.Administrador;

public class AdministradorDAO {
    
    public static void inserirAdm(Administrador adm) throws Exception{
        
            GenericoDAO.inserir(adm.getPessoa());
            GenericoDAO.inserir(adm);
            
        
    }
    
    public static Administrador consultarAdm(String cpf){
        
        List<Administrador> lista = GenericoDAO.consultar(Administrador.class,
        "idAdministrador", cpf);        
        Administrador a = lista.get(0);
        return a;
        
    }
    
    public static void excluirAdm(String cpf) throws Exception{
        
            Administrador ad = AdministradorDAO.consultarAdm(cpf);
            GenericoDAO.excluir(ad);
            
        
    }
    
}
