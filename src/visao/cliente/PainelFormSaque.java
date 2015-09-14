package visao.cliente;

import controle.MovimentacoesConta;
import controle.excecoes.FormularioExcecoes;
import controle.visao.FabricaComponentes;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Excecoes;
import visao.login.PainelLogin;

public class PainelFormSaque extends JPanel implements ActionListener{
    
    private FabricaComponentes fb;
    private JLabel lblSaque;
    private JTextField txtValor;
    private JButton btnSacar;

    public PainelFormSaque() {
        super.setLayout(new GridBagLayout());
        this.fb = new FabricaComponentes(this);
        
        this.lblSaque = fb.addLabel("Valor a ser sacado R$:", 0, 0);
        this.txtValor = fb.addTextField(10, 0, 1);
        
        this.btnSacar = fb.addBotao("Sacar", 0, 2);
        this.btnSacar.setActionCommand("s");
        this.btnSacar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FormularioExcecoes.campoVazio("VALOR", this.txtValor.getText());
            String campo = FormularioExcecoes.validarReal(
                    this.txtValor.getText(),"VALOR");
            MovimentacoesConta.saque(PainelLogin.getCpf(),
                    campo);
                    JOptionPane.showMessageDialog(null,
                            "Saque efetuado com sucesso!");
        }catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
        }catch (Exception ex) {
            Logger.getLogger(PainelFormSaque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
