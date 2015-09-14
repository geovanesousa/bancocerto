package visao.administrador;

import controle.GenericoDAO;
import controle.visao.FabricaComponentes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Agencia;
import visao.login.PainelLogin;

public class PainelAdministrador extends JPanel implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menuArquivo, menuIrPara, menuAjuda;
    private JMenuItem itemSair, itemAdm, itemAg, itemCliente, itemSobre;
    private FabricaComponentes fb;
    private JPanel painel;

    public PainelAdministrador() {

        super.setLayout(new BorderLayout());
        this.fb = new FabricaComponentes(this);
        this.menuBar = this.fb.addMenuBar();

        this.menuArquivo = this.fb.addMenu(this.menuBar, "Arquivo");
        this.menuIrPara = this.fb.addMenu(this.menuBar, "Editar");
        this.menuAjuda = this.fb.addMenu(this.menuBar, "Ajuda");

        this.itemSair = this.fb.addMenuItem(this.menuArquivo, "Sair");
        this.itemSair.setActionCommand("sair");
        this.itemSair.addActionListener(this);


        this.itemAdm = this.fb.addMenuItem(this.menuIrPara, "Administrador");
        this.itemAdm.setActionCommand("adm");
        this.itemAdm.addActionListener(this);

        this.itemAg = this.fb.addMenuItem(this.menuIrPara, "Agência");
        this.itemAg.setActionCommand("ag");
        this.itemAg.addActionListener(this);

        this.itemCliente = this.fb.addMenuItem(this.menuIrPara, "Cliente");
        this.itemCliente.setActionCommand("c");
        this.itemCliente.addActionListener(this);
        
        this.itemSobre = this.fb.addMenuItem(this.menuAjuda, "Sobre");
        this.itemSobre.setActionCommand("s");
        this.itemSobre.addActionListener(this);
       
        this.painel = new JPanel();
        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("adm")) {
                this.painel.setVisible(false);
                this.painel = new PainelFormAdm();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();

        }
        if (e.getActionCommand().equals("ag")) {
                this.painel.setVisible(false);
                this.painel = new PainelFormAg();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();
        }
        if (e.getActionCommand().equals("c")) {
            try {
                List l = GenericoDAO.listarTodos(Agencia.class);
                l.get(0);
                this.painel.setVisible(false);
                this.painel = new PainelFormCliente();
                this.fb.addPainel(this.painel);
                this.painel.setVisible(true);
                this.painel.updateUI();
            } catch (IndexOutOfBoundsException iobe) {
                
                JOptionPane.showMessageDialog(null,
                        "Um CLIENTE necessita de uma AGÊNCIA!\n"+
                        "Nenhuma AGÊNCIA Encontrada!\n"+
                        "Cadastre uma AGÊNCIA e tente novamente!",
                        "Erro",JOptionPane.ERROR_MESSAGE);
            }
                
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


