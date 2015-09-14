package visao;

import controle.AdministradorDAO;
import controle.ConexaoBD;
import controle.Utilitarios;
import controle.excecoes.FormularioExcecoes;
import controle.visao.FabricaComponentes;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.Administrador;
import modelo.Excecoes;
import modelo.Pessoa;

public class PainelPrimeiroAdm extends JPanel implements ActionListener {

    private JPanel formulario;
    private JLabel lblCpf, lblNome, lblDtNasc, lblemail, lblsenha;
    private JFormattedTextField txtCpf;
    private JTextField txtNome, txtEmail;
    private JFormattedTextField txtDtNasc;
    private JPasswordField txtSenha;
    private JPasswordField txtSenhaDois;
    private JButton btnInserir, btnLimpar;
    private String cpf, email, senha, senhaDois;

    public PainelPrimeiroAdm() {
        
        ConexaoBD.abrirConexaoBD();
        ConexaoBD.fecharConexao();
        
        super.setLayout(new BorderLayout());

        this.formulario = new JPanel(new GridBagLayout());
        FabricaComponentes fb = new FabricaComponentes(formulario);
        this.lblCpf = fb.addLabel("CPF: ", 0, 1);
        this.txtCpf = fb.addtxtFormatado("###.###.###-##", 15, 0, 2);
        this.lblNome = fb.addLabel("Nome: ", 0, 3);
        this.txtNome = fb.addTextField(40, 0, 4);
        this.lblDtNasc = fb.addLabel("Data de Nascimento: ", 0, 5);
        this.txtDtNasc = fb.addtxtFormatado("##/##/####", 10, 0, 6);
        this.lblemail = fb.addLabel("Email: ", 0, 7);
        this.txtEmail = fb.addTextField(40, 0, 8);
        this.lblsenha = fb.addLabel("Senha: ", 0, 9);
        this.txtSenha = fb.addCampoSenha(40, 0, 10);
        this.txtSenhaDois = fb.addCampoSenha(40, 0, 11);

        this.btnInserir = fb.addBotao("Inserir", 0, 12);
        this.btnLimpar = fb.addBotao("Limpar Campos", 0, 16);

        super.add(this.formulario, BorderLayout.CENTER);

        this.btnInserir.setActionCommand("i");
        this.btnInserir.addActionListener(this);

        this.btnLimpar.setActionCommand("lp");
        this.btnLimpar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("i")) {
            
            this.cpf = Utilitarios.campoSemCaracteres(this.txtCpf.getText());
            this.email = this.txtEmail.getText().trim();
            this.senha = String.valueOf(this.txtSenha.getPassword()).trim();
            this.senhaDois = String.valueOf(
                    this.txtSenhaDois.getPassword()).trim();
            
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                FormularioExcecoes.campoVazio("Campo NOME", 
                        this.txtNome.getText());
                FormularioExcecoes.campoVazio("Campo DATA NASC.",
                        Utilitarios.campoSemCaracteres(
                                this.txtDtNasc.getText()));
                FormularioExcecoes.campoVazio("Campo SENHA", this.senha);
                FormularioExcecoes.campoVazio("Campo SENHA", this.senhaDois);
                FormularioExcecoes.validarSenha(this.senha, this.senhaDois,
                        "As SENHAS Não Correspondem!");
                Pessoa p = new Pessoa();
                p.setCpf(this.cpf);
                p.setNome(this.txtNome.getText());
                p.setDataNascimento(Utilitarios.stringParaData(this.txtDtNasc.getText()));
                Administrador adm = new Administrador();
                adm.setPessoa(p);
                adm.setIdAdministrador(adm.getPessoa().getCpf());
                adm.setEmail(this.email);
                adm.setSenha(this.senha);
                AdministradorDAO.inserirAdm(adm);
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                
            } catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Já Existe um Administrador esse CPF!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        
        if (e.getActionCommand().equals("lp")) {
            this.txtCpf.setText("");
            this.txtNome.setText("");
            this.txtDtNasc.setText("");
            this.txtEmail.setText("");
            this.txtSenha.setText("");
            this.txtSenhaDois.setText("");
        }
    }

}
