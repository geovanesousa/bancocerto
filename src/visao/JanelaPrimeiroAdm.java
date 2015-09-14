package visao;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JanelaPrimeiroAdm extends JFrame{
    
    private Container c;
    private JPanel painel;
    
    public JanelaPrimeiroAdm(String titulo) {
        
        this.c = super.getContentPane();
        this.painel = new PainelPrimeiroAdm();
        this.c.add(this.painel);
        
        
        super.setTitle(titulo);
        this.confJanela();
    }
    public void confJanela(){
           
           super.setSize(500, 400);
           super.setResizable(false);
           super.setVisible(true);
           super.setLocationRelativeTo(null);
           super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
