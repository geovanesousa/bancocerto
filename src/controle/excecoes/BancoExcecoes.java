package controle.excecoes;

import controle.AdministradorDAO;
import controle.ClienteDAO;
import controle.GenericoDAO;
import java.util.List;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Excecoes;
import visao.login.PainelLogin;

public class BancoExcecoes {
    
    public static void verSeAdmExiste(String cpf) throws Excecoes{
            Administrador adm = AdministradorDAO.consultarAdm(cpf);
            if(adm!=null){
            throw new Excecoes("Já existe um ADMINISTRADOR com esse CPF!");
            }
        
    }
    public static void verSeClienteExiste(String cpf)
            throws Excecoes{
            Cliente c = ClienteDAO.consultarCliente(cpf);
            if(c!=null){
            throw new Excecoes("Já existe um CLIENTE com esse CPF!");
            }
        
    }
    
    public static void validarExclusaoAdm(String cpf)throws Excecoes{
        if(cpf.equals(PainelLogin.getCpf())){
            throw new Excecoes("Impossível EXCLUIR sua PRÓPRIA CONTA!");
        }
    }
}
