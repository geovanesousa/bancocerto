package visao.cliente;

import controle.ClienteDAO;
import controle.visao.FabricaComponentes;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Conta;
import visao.login.PainelLogin;

public class PainelFormSaldo extends JPanel{
    
    private FabricaComponentes fb;
    private JLabel lblSaldo;
    private JTextField txtSaldo;

    public PainelFormSaldo() {
        
        super.setLayout(new GridBagLayout());
        this.fb = new FabricaComponentes(this);
        
        this.lblSaldo = fb.addLabel("Seu saldo é R$:", 0, 0);
        this.txtSaldo = fb.addTextField(15, 1, 0);
        this.txtSaldo.setEditable(false);
        
        Conta c = (ClienteDAO.consultarCliente(PainelLogin.getCpf()).getConta());
        this.txtSaldo.setText(String.valueOf(c.getSaldo()));
        
        
    }
    
    
    
}
