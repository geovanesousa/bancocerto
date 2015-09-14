package visao.administrador;

import controle.AdministradorDAO;
import controle.GenericoDAO;
import controle.Utilitarios;
import controle.excecoes.BancoExcecoes;
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

public class PainelFormAdm extends JPanel implements ActionListener {

    private JPanel formulario, painelTab;
    private JLabel lblCpf, lblNome, lblDtNasc, lblemail, lblsenha;
    private JFormattedTextField txtCpf;
    private JTextField txtNome, txtEmail;
    private JFormattedTextField txtDtNasc;
    private JPasswordField txtSenha;
    private JPasswordField txtSenhaDois;
    private JButton btnInserir, btnConsultar, btnAlterar,
            btnExcluir, btnLimpar;
    private String cpf, email, senha, senhaDois;

    public PainelFormAdm() {

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
        this.btnConsultar = fb.addBotao("Consultar", 1, 12);
        this.btnAlterar = fb.addBotao("Alterar", 2, 12);
        this.btnExcluir = fb.addBotao("Excluir", 0, 13);
        this.btnLimpar = fb.addBotao("Limpar Campos", 1, 13);

        super.add(this.formulario, BorderLayout.WEST);

        this.btnInserir.setActionCommand("i");
        this.btnInserir.addActionListener(this);

        this.btnConsultar.setActionCommand("c");
        this.btnConsultar.addActionListener(this);

        this.btnAlterar.setActionCommand("a");
        this.btnAlterar.addActionListener(this);

        this.btnExcluir.setActionCommand("e");
        this.btnExcluir.addActionListener(this);

        this.btnLimpar.setActionCommand("lp");
        this.btnLimpar.addActionListener(this);

        this.painelTab = new JPanel(new BorderLayout());
        this.add(this.painelTab, BorderLayout.CENTER);

        FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
        fb3.addTabelaAdm();

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
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaAdm();
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
        if (e.getActionCommand().equals("c")) {
            
            this.cpf = Utilitarios.campoSemCaracteres(this.txtCpf.getText());
            this.email = this.txtEmail.getText().trim();
            this.senha = String.valueOf(this.txtSenha.getPassword()).trim();
            this.senhaDois = String.valueOf(
                    this.txtSenhaDois.getPassword()).trim();
            
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                Administrador adm = AdministradorDAO.consultarAdm(this.cpf);
                this.txtNome.setText(adm.getPessoa().getNome());
                this.txtDtNasc.setText(Utilitarios.dataParaString(
                        adm.getPessoa().getDataNascimento()));
                this.txtEmail.setText(adm.getEmail());
                this.txtSenha.setText(adm.getSenha());
                this.txtSenhaDois.setText(adm.getSenha());
            } catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null,
                        "Administrador não Encontrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand().equals("a")) {
            
            this.cpf = Utilitarios.campoSemCaracteres(this.txtCpf.getText());
            this.email = this.txtEmail.getText().trim();
            this.senha = String.valueOf(this.txtSenha.getPassword()).trim();
            this.senhaDois = String.valueOf(
                    this.txtSenhaDois.getPassword()).trim();
            
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                FormularioExcecoes.campoVazio("Campo NOME", this.txtNome.getText());
                FormularioExcecoes.campoVazio("Campo DATA NASC.",
                        Utilitarios.campoSemCaracteres(this.txtDtNasc.getText()));
                FormularioExcecoes.campoVazio("Campo SENHA", this.senha);
                FormularioExcecoes.campoVazio("Campo SENHA", this.senhaDois);
                FormularioExcecoes.validarSenha(this.senha, this.senhaDois,
                        "As SENHAS Não Correspondem!");
                Pessoa p = new Pessoa();
                p.setCpf(this.cpf);
                p.setNome(this.txtNome.getText());
                p.setDataNascimento(Utilitarios.stringParaData(
                        this.txtDtNasc.getText()));
                Administrador adm = new Administrador();
                adm.setPessoa(p);
                adm.setIdAdministrador(adm.getPessoa().getCpf());
                adm.setEmail(this.email);
                adm.setSenha(this.senha);
                GenericoDAO.atualizar(adm);
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaAdm();
            } catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getActionCommand().equals("e")) {
            
            this.cpf = Utilitarios.campoSemCaracteres(this.txtCpf.getText());
            this.email = this.txtEmail.getText().trim();
            this.senha = String.valueOf(this.txtSenha.getPassword()).trim();
            this.senhaDois = String.valueOf(
                    this.txtSenhaDois.getPassword()).trim();
            
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                BancoExcecoes.validarExclusaoAdm(this.cpf);
                AdministradorDAO.excluirAdm(this.cpf);
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaAdm();
            }catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null,
                        "Administrador não Encontrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
