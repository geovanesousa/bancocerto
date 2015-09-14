package controle;

import java.util.List;
import modelo.Cliente;


public class ClienteDAO {

    public static Cliente inserirCliente(Cliente c) throws Exception {

        GenericoDAO.inserir(c.getPessoa());
        GenericoDAO.inserir(c.getEndereco());
        GenericoDAO.inserir(c.getConta());
        GenericoDAO.inserir(c);
        return c;
    }

    public static Cliente consultarCliente(String cpf) {
        
        List<Cliente> lista = GenericoDAO.consultar(Cliente.class, "idCliente", cpf);
        Cliente c = lista.get(0);
        return c;
        
    }

    public static void excluirCliente(String cpf) throws Exception {

        Cliente c = ClienteDAO.consultarCliente(cpf);
        GenericoDAO.excluir(c);

    }
}
