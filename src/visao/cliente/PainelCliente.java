package visao.cliente;

import controle.visao.FabricaComponentes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import visao.login.PainelLogin;

public class PainelCliente extends JPanel implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menuArquivo, menuIrPara, menuAjuda;
    private JMenuItem itemSair, itemSaque, itemDep, itemSaldo,
            itemTransf, itemSobre;
    private FabricaComponentes fb;
    private JPanel painel;

    public PainelCliente() {

        super.setLayout(new BorderLayout());
        this.fb = new FabricaComponentes(this);
        this.menuBar = this.fb.addMenuBar();

        this.menuArquivo = this.fb.addMenu(this.menuBar, "Arquivo");
        this.menuIrPara = this.fb.addMenu(this.menuBar, "Efetuar");
        this.menuAjuda = this.fb.addMenu(this.menuBar, "Ajuda");

        this.itemSair = this.fb.addMenuItem(this.menuArquivo, "Sair");
        this.itemSair.setActionCommand("sair");
        this.itemSair.addActionListener(this);


        this.itemSaque = this.fb.addMenuItem(this.menuIrPara, "Saque");
        this.itemSaque.setActionCommand("saque");
        this.itemSaque.addActionListener(this);

        this.itemDep = this.fb.addMenuItem(this.menuIrPara, "Depósito");
        this.itemDep.setActionCommand("dep");
        this.itemDep.addActionListener(this);

        this.itemTransf = this.fb.addMenuItem(this.menuIrPara, "Transferência");
        this.itemTransf.setActionCommand("tr");
        this.itemTransf.addActionListener(this);
        
        this.itemSaldo = this.fb.addMenuItem(this.menuIrPara, "Saldo");
        this.itemSaldo.setActionCommand("sl");
        this.itemSaldo.addActionListener(this);
        
        this.itemSobre = this.fb.addMenuItem(this.menuAjuda, "Sobre");
        this.itemSobre.setActionCommand("s");
        this.itemSobre.addActionListener(this);
        
        /*this.setBorder(BorderFactory.createTitledBorder(
                        "USUÁRIO CLIENTE"));*/

        this.painel = new JPanel();
        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("saque")) {
                
                this.painel.setVisible(false);
                this.painel = new PainelFormSaque();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();

        }
        if (e.getActionCommand().equals("dep")) {
                
                this.painel.setVisible(false);
                this.painel = new PainelFormDep();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();
        }
        if (e.getActionCommand().equals("tr")) {
                
                this.painel.setVisible(false);
                this.painel = new PainelFormTransf();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();
        }
        if (e.getActionCommand().equals("sl")) {
                
                this.painel.setVisible(false);
                this.painel = new PainelFormSaldo();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();
        }
        if(e.getActionCommand().equals("sair")){
            this.setVisible(false);
            this.removeAll();
            this.add(new PainelLogin(),BorderLayout.CENTER);
            this.setVisible(true);
            this.updateUI();
        }
        if(e.getActionCommand().equals("s")){
            JOptionPane.showMessageDialog(null, "     BANCO CERTO\n"
                                              + "Seu Banco Certo! Certo?\n"
                                              + "  Geovane Sousa - 2014", 
                    "Sobre o Sistema", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}


