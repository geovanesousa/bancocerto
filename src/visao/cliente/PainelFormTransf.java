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

public class PainelFormTransf extends JPanel implements ActionListener{
    
    private FabricaComponentes fb;
    private JLabel lblConta,lblValor;
    private JTextField txtConta,txtValor;
    private JButton btnTransferir;

    public PainelFormTransf() {
        super.setLayout(new GridBagLayout());
        this.fb = new FabricaComponentes(this);
        
        this.lblConta = fb.addLabel("Conta de destino:", 0, 0);
        this.txtConta = fb.addTextField(10, 0, 1);
        this.lblValor = fb.addLabel("Valor a ser transferido:", 0, 2);
        this.txtValor = fb.addTextField(10, 0, 3);
        
        this.btnTransferir = fb.addBotao("Transferência", 0, 4);
        this.btnTransferir.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FormularioExcecoes.campoVazio("Campo CONTA", this.txtConta.getText());
            FormularioExcecoes.validarCampoNumerico(
                    this.txtConta.getText(),"CONTA");
            FormularioExcecoes.campoVazio("VALOR", this.txtValor.getText());
            String campo = FormularioExcecoes.validarReal(
                    this.txtValor.getText(),"VALOR");
            MovimentacoesConta.transferencia(PainelLogin.getCpf(),
                    Integer.parseInt(this.txtConta.getText()), campo);
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,
                        "CONTA não encontrada!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
            Logger.getLogger(PainelFormTransf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
