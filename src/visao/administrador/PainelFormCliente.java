package visao.administrador;

import controle.AgenciaDAO;
import controle.ClienteDAO;
import controle.ContaDAO;
import controle.GenericoDAO;
import controle.Utilitarios;
import controle.excecoes.FormularioExcecoes;
import controle.visao.FabricaComponentes;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.Agencia;
import modelo.Cliente;
import modelo.Conta;
import modelo.Endereco;
import modelo.Excecoes;
import modelo.Pessoa;

public class PainelFormCliente extends JPanel implements ActionListener {

    private JPanel formulario, painelTab;
    private JLabel lblCpf, lblNome, lblDtNasc, lblcodAgencia, lblConta, lblCep, lblRua,
            lblNumero, lblComplemento, lblBairro, lblCidade, lblUf, lblSenha;
    private JFormattedTextField txtCpf, txtCep;
    private JComboBox cbAgencia;
    private JTextField txtNome, txtDtNasc, txtConta, txtRua, txtNumero,
            txtComplemento, txtBairro, txtCidade, txtUf;
    private JButton btnInserir, btnConsultar, btnAlterar,
            btnExcluir, btnLimpar;
    private JPasswordField txtSenha, txtSenhaDois;
    private List<Agencia> listaAg;
    String cpf, cep, uf, senha, senhaDois;

    public PainelFormCliente() {

        super.setLayout(new BorderLayout());

        this.formulario = new JPanel(new GridBagLayout());
        FabricaComponentes fb = new FabricaComponentes(formulario);
        this.lblCpf = fb.addLabel("CPF:", 0, 0);
        this.txtCpf = fb.addtxtFormatado("###.###.###-##", 15, 1, 0);
        this.lblNome = fb.addLabel("Nome:", 0, 2);
        this.txtNome = fb.addTextField(20, 0, 3);
        this.lblDtNasc = fb.addLabel("Data de Nascimento: ", 0, 4);
        this.txtDtNasc = fb.addtxtFormatado("##/##/####", 10, 0, 5);
        this.lblcodAgencia = fb.addLabel("Ag.:", 0, 6);
        this.listaAg = GenericoDAO.listarTodos(Agencia.class);
        this.cbAgencia = fb.addCbAgencia(1, 6, listaAg);
        this.lblConta = fb.addLabel("Conta:", 0, 8);
        this.txtConta = fb.addTextField(10, 1, 8);
        this.txtConta.setEditable(false);
        this.lblCep = fb.addLabel("CEP:", 0, 10);
        this.txtCep = fb.addtxtFormatado("##.###-###", 10, 1, 10);
        this.lblRua = fb.addLabel("Rua:", 0, 12);
        this.txtRua = fb.addTextField(15, 0, 13);
        this.lblNumero = fb.addLabel("Número", 0, 14);
        this.txtNumero = fb.addTextField(10, 1, 14);
        this.lblComplemento = fb.addLabel("Compl.:", 0, 16);
        this.txtComplemento = fb.addTextField(15, 1, 16);
        this.lblBairro = fb.addLabel("Bairro:", 0, 18);
        this.txtBairro = fb.addTextField(15, 0, 19);
        this.lblCidade = fb.addLabel("Cidade:", 0, 20);
        this.txtCidade = fb.addTextField(15, 0, 21);
        this.lblUf = fb.addLabel("UF:", 0, 22);
        this.txtUf = fb.addTextField(2, 1, 22);
        this.lblSenha = fb.addLabel("Senha: ", 0, 24);
        this.txtSenha = fb.addCampoSenha(15, 0, 25);
        this.txtSenhaDois = fb.addCampoSenha(15, 0, 26);

        this.btnInserir = fb.addBotao("Inserir", 0, 27);
        this.btnConsultar = fb.addBotao("Consultar", 1, 27);
        this.btnAlterar = fb.addBotao("Alterar", 2, 27);
        this.btnExcluir = fb.addBotao("Excluir", 0, 28);
        this.btnLimpar = fb.addBotao("Limpar Campos", 1, 28);

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
        fb3.addTabelaCliente();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.cpf = Utilitarios.campoSemCaracteres(this.txtCpf.getText());
        this.cep = Utilitarios.campoSemCaracteres(this.txtCep.getText());
        this.uf = Utilitarios.campoSemCaracteres(this.txtUf.getText());
        this.senha = String.valueOf(this.txtSenha.getPassword()).trim();
        this.senhaDois = String.valueOf(
                this.txtSenhaDois.getPassword()).trim();

        if (e.getActionCommand().equals("i")) {
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                FormularioExcecoes.campoVazio("Campo NOME",
                        this.txtNome.getText());
                FormularioExcecoes.campoVazio("Campo DATA NASC.",
                        Utilitarios.campoSemCaracteres(
                                this.txtDtNasc.getText()));
                if (!this.cep.isEmpty() || !this.txtRua.getText().isEmpty()
                        || !this.txtNumero.getText().isEmpty()
                        || !this.txtComplemento.getText().isEmpty()
                        || !this.txtBairro.getText().isEmpty()
                        || !this.txtCidade.getText().isEmpty()
                        || !this.uf.isEmpty()) {
                    FormularioExcecoes.campoVazio("Campo CEP", this.cep);
                    FormularioExcecoes.campoVazio("Campo RUA",
                            this.txtRua.getText());
                    FormularioExcecoes.campoVazio("Campo NÚMERO",
                            this.txtNumero.getText());
                    FormularioExcecoes.validarCampoNumerico(
                            this.txtNumero.getText(), "NÚMERO");
                    FormularioExcecoes.campoVazio("Campo BAIRRO",
                            Utilitarios.campoSemCaracteres(
                                    this.txtBairro.getText()));
                    FormularioExcecoes.campoVazio("Campo CIDADE",
                            Utilitarios.campoSemCaracteres(
                                    this.txtCidade.getText()));
                    FormularioExcecoes.campoVazio("Campo UF", this.uf);
                    FormularioExcecoes.validarTamanho(
                            "Para o campo UF digite apenas a sigla correspondente:\n"
                            + "Ex.: SP, RJ, CE, entre outros.", this.uf, 2);
                }
                FormularioExcecoes.campoVazio("Campo SENHA", this.senha);
                FormularioExcecoes.campoVazio("Campo SENHA", this.senhaDois);
                FormularioExcecoes.validarSenha(this.senha, this.senhaDois,
                        "As SENHAS Não Correspondem!");
                Pessoa p = new Pessoa();
                p.setCpf(this.cpf);
                p.setNome(this.txtNome.getText());
                p.setDataNascimento(Utilitarios.stringParaData(this.txtDtNasc.getText()));
                Endereco end = new Endereco();
                end.setIdEndereco(0);
                end.setCep(this.cep);
                end.setRua(this.txtRua.getText());
                end.setNumero(Integer.parseInt(this.txtNumero.getText()));
                end.setComplemento(this.txtComplemento.getText());
                end.setBairro(this.txtBairro.getText());
                end.setCidade(this.txtCidade.getText());
                end.setUf(this.uf);
                Conta c = new Conta();
                c.setNumConta(0);
                c.setCpfCliente(this.cpf);
                c.setCodAgencia(Integer.parseInt(
                        this.cbAgencia.getSelectedItem().toString()));
                c.setSaldo(0.0);
                Cliente cliente = new Cliente();
                cliente.setPessoa(p);
                cliente.setEndereco(end);
                cliente.setAgencia(AgenciaDAO.consultaAg(Integer.parseInt(
                        this.cbAgencia.getSelectedItem().toString())));
                cliente.setIdCliente(this.cpf);
                cliente.setSenha(this.senha);
                cliente.setConta(c);
                ClienteDAO.inserirCliente(cliente);
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
                this.txtConta.setText(String.valueOf(
                        ContaDAO.ultimaConta().getNumConta()));
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaCliente();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "Campo NÚMERO contem espaços em branco!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Já existe um CLIENTE com esse CPF!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getActionCommand().equals("c")) {
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                Cliente c = ClienteDAO.consultarCliente(this.cpf);
                this.txtNome.setText(c.getPessoa().getNome());
                this.txtDtNasc.setText(Utilitarios.dataParaString(
                        c.getPessoa().getDataNascimento()));
                this.cbAgencia.setSelectedItem(c.getAgencia().getCodAgencia());
                this.txtConta.setText(String.valueOf(c.getConta().getNumConta()));
                this.txtCep.setText(c.getEndereco().getCep());
                this.txtRua.setText(c.getEndereco().getRua());
                this.txtNumero.setText(String.valueOf(
                        c.getEndereco().getNumero()));
                this.txtComplemento.setText(c.getEndereco().getComplemento());
                this.txtBairro.setText(c.getEndereco().getBairro());
                this.txtCidade.setText(c.getEndereco().getCidade());
                this.txtUf.setText(c.getEndereco().getUf());
                this.txtSenha.setText(c.getSenha());
                this.txtSenhaDois.setText(c.getSenha());
            } catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null,
                        "CLIENTE não encontrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getActionCommand().equals("a")) {
            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cep);
                FormularioExcecoes.campoVazio("Campo NOME",
                        this.txtNome.getText());
                FormularioExcecoes.campoVazio("Campo DATA NASC.",
                        Utilitarios.campoSemCaracteres(
                                this.txtDtNasc.getText()));
                if (!this.cep.isEmpty() || !this.txtRua.getText().isEmpty()
                        || !this.txtNumero.getText().isEmpty()
                        || !this.txtComplemento.getText().isEmpty()
                        || !this.txtBairro.getText().isEmpty()
                        || !this.txtCidade.getText().isEmpty()
                        || !this.uf.isEmpty()) {
                    FormularioExcecoes.campoVazio("Campo CEP", this.cep);
                    FormularioExcecoes.campoVazio("Campo RUA",
                            this.txtRua.getText());
                    FormularioExcecoes.campoVazio("Campo NÚMERO",
                            this.txtNumero.getText());
                    FormularioExcecoes.validarCampoNumerico(
                            this.txtNumero.getText(), "NÚMERO");
                    FormularioExcecoes.campoVazio("Campo BAIRRO",
                            Utilitarios.campoSemCaracteres(
                                    this.txtBairro.getText()));
                    FormularioExcecoes.campoVazio("Campo CIDADE",
                            Utilitarios.campoSemCaracteres(
                                    this.txtCidade.getText()));
                    FormularioExcecoes.campoVazio("Campo UF", this.uf);
                    FormularioExcecoes.validarTamanho(
                            "Para o campo UF digite apenas a sigla correspondente:\n"
                            + "Ex.: SP, RJ, CE, entre outros.", this.uf, 2);
                }
                FormularioExcecoes.campoVazio("Campo SENHA", this.senha);
                FormularioExcecoes.campoVazio("Campo SENHA", this.senhaDois);
                FormularioExcecoes.validarSenha(this.senha, this.senhaDois,
                        "As SENHAS Não Correspondem!");
                Cliente cliente = ClienteDAO.consultarCliente(this.cpf);
                Pessoa p = new Pessoa();
                p.setCpf(this.cpf);
                p.setNome(this.txtNome.getText());
                p.setDataNascimento(Utilitarios.stringParaData(
                        this.txtDtNasc.getText()));
                Agencia a = (AgenciaDAO.consultaAg(Integer.parseInt(
                        this.cbAgencia.getSelectedItem().toString())));
                Conta c = cliente.getConta();
                Endereco end = new Endereco();
                end.setIdEndereco(cliente.getEndereco().getIdEndereco());
                end.setCep(this.cep);
                end.setRua(this.txtRua.getText());
                end.setNumero(Integer.parseInt(this.txtNumero.getText()));
                end.setComplemento(this.txtComplemento.getText());
                end.setBairro(this.txtBairro.getText());
                end.setCidade(this.txtCidade.getText());
                end.setUf(this.uf);
                cliente.setIdCliente(this.cpf);
                cliente.setPessoa(p);
                cliente.setEndereco(end);
                cliente.setAgencia(a);
                cliente.setConta(c);
                cliente.setSenha(this.senha);
                GenericoDAO.atualizar(cliente);
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaCliente();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "Campo NÚMERO contem espaços em branco!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch (Excecoes ex) {
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

            try {
                FormularioExcecoes.campoVazio("Campo CPF", this.cpf);
                ClienteDAO.excluirCliente(this.cpf);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                this.painelTab = new JPanel(new BorderLayout());
                this.add(this.painelTab, BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painelTab);
                fb3.addTabelaCliente();
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null,
                        "CLIENTE não econtrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
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
        if (e.getActionCommand().equals("lp")) {
            this.txtCpf.setText("");
            this.txtNome.setText("");
            this.txtDtNasc.setText("");
            this.cbAgencia.setSelectedIndex(0);
            this.txtConta.setText("");
            this.txtCep.setText("");
            this.txtRua.setText("");
            this.txtNumero.setText("");
            this.txtComplemento.setText("");
            this.txtBairro.setText("");
            this.txtCidade.setText("");
            this.txtUf.setText("");
            this.txtSenha.setText("");
            this.txtSenhaDois.setText("");
        }

    }
}
