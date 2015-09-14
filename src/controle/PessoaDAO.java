package controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Pessoa;

public class PessoaDAO {
    
    public static Pessoa consultarPessoa(String cpf){
        
        List<Pessoa> lista = GenericoDAO.consultar(Pessoa.class, "cpf", cpf);        
        Pessoa p = lista.get(0);
        return p;
        
    }
}
