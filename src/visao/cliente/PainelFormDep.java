package visao.cliente;

import controle.MovimentacoesConta;
import controle.excecoes.FormularioExcecoes;
import controle.visao.FabricaComponentes;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Excecoes;
import visao.login.PainelLogin;

public class PainelFormDep extends JPanel implements ActionListener{
    
    private FabricaComponentes fb;
    private JLabel lblValor;
    private JTextField txtValor;
    private JButton btnDep;

    public PainelFormDep() {
        
        super.setLayout(new GridBagLayout());
        this.fb = new FabricaComponentes(this);
        
        this.lblValor = fb.addLabel("Valor do Depósito R$:", 0, 0);
        this.txtValor = fb.addTextField(10, 0, 1);
        
        this.btnDep = fb.addBotao("Depositar", 0, 2);
        this.btnDep.setActionCommand("d");
        this.btnDep.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FormularioExcecoes.campoVazio("VALOR", this.txtValor.getText());
            String campo = FormularioExcecoes.validarReal(
                    this.txtValor.getText(),"VALOR");
        MovimentacoesConta.deposito(PainelLogin.getCpf(), campo);
          
        }catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
        }
}
}
