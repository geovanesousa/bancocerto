package visao.login;

import controle.AdministradorDAO;
import controle.ClienteDAO;
import controle.ConexaoBD;
import controle.Utilitarios;
import controle.excecoes.FormularioExcecoes;
import modelo.Excecoes;
import controle.visao.FabricaComponentes;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import modelo.Administrador;
import modelo.Cliente;
import visao.administrador.PainelAdministrador;
import visao.cliente.PainelCliente;

public class PainelLogin extends JPanel implements ActionListener {

    private FabricaComponentes fb;
    private JPanel painel, painelForm, novoPainel;
    private JLabel lblCpf, lblSenha, lblUsuario;
    private JFormattedTextField txtCpf;
    private JPasswordField txtSenha;
    private JComboBox cbUsuario;
    private JButton btnEntrar, btnLimpar;
    private static String cpf;

    public PainelLogin() {
        
        ConexaoBD.abrirConexaoBD();
        ConexaoBD.fecharConexao();

        super.setLayout(new BorderLayout());
        this.painel = new JPanel(new BorderLayout());
        this.painel.setBorder(BorderFactory.createTitledBorder("TELA DE LOGIN"));
        this.painelForm = new JPanel(new GridBagLayout());
        this.fb = new FabricaComponentes(this.painelForm);
        this.lblCpf = fb.addLabel("CPF:", 0, 0);
        this.txtCpf = fb.addtxtFormatado("###.###.###-##", 14, 0, 1);
        this.lblSenha = fb.addLabel("Senha:", 0, 2);
        this.txtSenha = fb.addCampoSenha(40, 0, 3);
        this.lblUsuario = fb.addLabel("Tipo de Usuário:", 0, 4);
        this.cbUsuario = fb.addCbUsuarios(0, 5);
        this.painel.add(this.painelForm, BorderLayout.CENTER);

        this.btnEntrar = fb.addBotao("Entrar", 0, 6);
        this.btnEntrar.setActionCommand("e");
        this.btnEntrar.addActionListener(this);

        this.btnLimpar = fb.addBotao("Limpar", 1, 6);
        this.btnLimpar.setActionCommand("l");
        this.btnLimpar.addActionListener(this);

        this.add(this.painel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("e")) {

            Administrador adm = null;
            Cliente c = null;

            try {
                PainelLogin.cpf = Utilitarios.campoSemCaracteres(
                        this.txtCpf.getText());
                FormularioExcecoes.campoVazio("Campo CPF", PainelLogin.cpf);
                FormularioExcecoes.campoVazio("Campo SENHA", String.valueOf(
                        this.txtSenha.getPassword()));
                FormularioExcecoes.campoVazio("TIPO DE USUÁRIO",
                        this.cbUsuario.getSelectedItem().toString());
                if (this.cbUsuario.getSelectedItem().toString().equals("Administrador")) {
                    adm = AdministradorDAO.consultarAdm(PainelLogin.cpf);
                    FormularioExcecoes.validarSenha(adm.getSenha(), String.valueOf(
                            this.txtSenha.getPassword()),
                            "USUÁRIO ou SENHA inválidos!");
                    this.painel.setVisible(false);
                    this.novoPainel = new PainelAdministrador();
                    this.add(this.novoPainel, BorderLayout.CENTER);
                    this.updateUI();
                }
                if (this.cbUsuario.getSelectedItem().toString().equals("Cliente")) {
                    c = ClienteDAO.consultarCliente(PainelLogin.cpf);
                    FormularioExcecoes.validarSenha(c.getSenha(), String.valueOf(
                            this.txtSenha.getPassword()),
                            "USUÁRIO ou SENHA inválidos!");
                    this.painel.setVisible(false);
                    this.novoPainel = new PainelCliente();
                    this.add(this.novoPainel, BorderLayout.CENTER);
                    this.updateUI();
                }

            } catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IndexOutOfBoundsException erro) {
                JOptionPane.showMessageDialog(null,
                        "Usuário ou Senha inválidos!", "Erro",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
        if (e.getActionCommand().equals("l")) {
            this.txtCpf.setText("");
            this.txtSenha.setText("");
            this.cbUsuario.setSelectedIndex(0);
        }
    }

    public static String getCpf() {
        return cpf;
    }

}
