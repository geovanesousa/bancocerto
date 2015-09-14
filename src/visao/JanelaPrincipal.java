package visao;


import controle.GenericoDAO;
import java.awt.Container;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Administrador;
import visao.login.PainelLogin;

public class JanelaPrincipal extends JFrame{
    
    private Container c;
    private JPanel painel;
    
    public JanelaPrincipal(String titulo) {
        
        this.c = super.getContentPane();
        this.painel = new PainelLogin();
        this.c.add(this.painel);
        
        
        super.setTitle(titulo);
        this.confJanela();
        
        try{
        List l = GenericoDAO.listarTodos(Administrador.class);
        l.get(0);
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,
                    "NÃO exitem ADMINISTRADORES no sistema!\n"
                  + "Cadastre no FORMULÁRIO a seguir!","Erro",
                  JOptionPane.ERROR_MESSAGE);
            JanelaPrimeiroAdm jpa = new JanelaPrimeiroAdm(
                    "Cadastrar usuario ADMINISTRADOR:");
        }
        
    }
    public void confJanela(){
           
           super.setSize(800, 600);
           super.setVisible(true);
           super.setLocationRelativeTo(null);
           super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
