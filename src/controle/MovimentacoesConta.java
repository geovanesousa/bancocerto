package controle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conta;
import modelo.Excecoes;
import modelo.Pessoa;

public class MovimentacoesConta {
    
    public static void transferencia(String cpfRemetente,
            Integer numContaDestino, String v) throws Exception {
        
        Double valor = Utilitarios.txtParaDouble(v);
        
        if (valor > 0) {
            Conta a = ClienteDAO.consultarCliente(cpfRemetente).getConta();
            if(numContaDestino == a.getNumConta()){
                throw new Excecoes(
                        "Impossível transferir para a sua PRÓPRIA CONTA!");
            }
            Conta b = ContaDAO.consultaConta(numContaDestino);
            Pessoa p = PessoaDAO.consultarPessoa(b.getCpfCliente());

            if (a.getSaldo() > valor) {

                a.setSaldo((a.getSaldo() - valor));
                b.setSaldo((b.getSaldo() + valor));
                Integer retorno = JOptionPane.showConfirmDialog(null, "Conta de destino: "
                +numContaDestino+"\nNome do destinatário: "
                        +p.getNome()+
                        "\nValor a ser transferido: R$"+valor);
                if(retorno==0){
                GenericoDAO.atualizar(a);
                GenericoDAO.atualizar(b);
                JOptionPane.showMessageDialog(null, 
                        "Transferência efetuada com sucesso!");
                }

            } 
            if(a.getSaldo()< valor) {
                throw new Excecoes("Saldo insuficiente!");
            } 
            
        }else {
            throw new Excecoes("Valor deve ser maior que zero!");
            }
    }
    

    public static void saque(String cpf, String v) throws Exception {
        Double valor = Utilitarios.txtParaDouble(v);
        if (valor > 0) {
            Conta a = ClienteDAO.consultarCliente(cpf).getConta();
            if (a.getSaldo() > valor) {

                a.setSaldo((a.getSaldo() - valor));
                GenericoDAO.atualizar(a);
            } else {
                throw new Excecoes("Saldo insuficiente!");
            }

        } else {
            throw new Excecoes("Valor deve ser maior que zero!");
        }
    }

    public static void deposito(String cpf, String v) throws Excecoes{
        
        Double valor = Utilitarios.txtParaDouble(v);
        
        if (valor > 0) {
            Conta a = ClienteDAO.consultarCliente(cpf).getConta();
            a.setSaldo((a.getSaldo() + valor));
            try {
                GenericoDAO.atualizar(a);
                JOptionPane.showMessageDialog(null,
                        "Depósito realizado com sucesso!");
            } catch (Exception ex) {
                Logger.getLogger(MovimentacoesConta.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            throw new Excecoes("Valor deve ser maior que zero!");

        }
    }    
}


    

