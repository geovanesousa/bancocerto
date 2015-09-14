package visao.administrador;

import controle.AgenciaDAO;
import controle.GenericoDAO;
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
import javax.swing.JTextField;
import modelo.Agencia;
import modelo.Endereco;
import modelo.Excecoes;

public class PainelFormAg extends JPanel implements ActionListener {

    private JPanel formulario, painetTab;
    private JLabel lblcodBanco, lblcodAgencia, lblCep, lblRua, 
            lblNumero,lblComplemento,lblBairro,lblCidade,lblUf;
    private JFormattedTextField txtCep;
    private JTextField txtcodBanco, txtCodAgencia,txtRua,txtNumero,
            txtComplemento,txtBairro, txtCidade, txtUf;
    private JButton btnInserir, btnConsultar, btnAlterar,
            btnExcluir, btnLimpar;
    private String cep,uf;

    public PainelFormAg() {

        super.setLayout(new BorderLayout());

        this.formulario = new JPanel(new GridBagLayout());
        FabricaComponentes fb = new FabricaComponentes(formulario);
        this.lblcodBanco = fb.addLabel("Banco:", 0, 0);
        this.txtcodBanco = fb.addTextField(2, 0, 1);
        this.txtcodBanco.setText("17");
        this.txtcodBanco.setEditable(false);
        this.lblcodAgencia = fb.addLabel("Agencia:", 0, 2);
        this.txtCodAgencia = fb.addTextField(10, 0, 3);
        this.lblCep = fb.addLabel("CEP:", 0, 4);
        this.txtCep = fb.addtxtFormatado("##.###-###", 10, 0, 5);
        this.lblRua = fb.addLabel("Rua:", 0, 6);
        this.txtRua = fb.addTextField(40, 0, 7);
        this.lblNumero = fb.addLabel("Número", 0, 8);
        this.txtNumero = fb.addTextField(10, 0, 9);
        this.lblComplemento = fb.addLabel("Complemento:", 0, 10);
        this.txtComplemento = fb.addTextField(40, 0, 11);
        this.lblBairro = fb.addLabel("Bairro:", 0, 12);
        this.txtBairro = fb.addTextField(40, 0, 13);
        this.lblCidade = fb.addLabel("Cidade:", 0, 14);
        this.txtCidade = fb.addTextField(40, 0, 15);
        this.lblUf = fb.addLabel("UF:", 0, 16);
        this.txtUf = fb.addTextField(2, 0, 17);
        
        this.btnInserir = fb.addBotao("Inserir", 0, 18);
        this.btnConsultar = fb.addBotao("Consultar", 1, 18);
        this.btnAlterar = fb.addBotao("Alterar", 2, 18);
        this.btnExcluir = fb.addBotao("Excluir", 0, 19);
        this.btnLimpar = fb.addBotao("Limpar Campos", 1, 19);

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
        
        this.painetTab = new JPanel(new BorderLayout());
        super.add(this.painetTab,BorderLayout.CENTER);
        FabricaComponentes fb3 = new FabricaComponentes(this.painetTab);
        fb3.addTabelaAg();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand() .equals("i")) {
            try{
            this.cep = Utilitarios.campoSemCaracteres(this.txtCep.getText());
            this.uf = Utilitarios.campoSemCaracteres(this.txtUf.getText());
            FormularioExcecoes.campoVazio("Campo CEP", this.cep);
            FormularioExcecoes.campoVazio("Campo RUA",
                        this.txtRua.getText());
                FormularioExcecoes.campoVazio("Campo NÚMERO",
                        this.txtNumero.getText());
                FormularioExcecoes.validarCampoNumerico(
                        this.txtNumero.getText().trim(),"NÚMERO");
                FormularioExcecoes.campoVazio("Campo BAIRRO", 
                        Utilitarios.campoSemCaracteres(
                                this.txtBairro.getText()));
                FormularioExcecoes.campoVazio("Campo CIDADE", 
                        Utilitarios.campoSemCaracteres(
                                this.txtCidade.getText()));
                FormularioExcecoes.campoVazio("Campo UF", this.uf);
                FormularioExcecoes.validarTamanho(
                        "Para o campo UF digite apenas a sigla correspondente:\n"
                       +"Ex.: SP, RJ, CE, entre outros.", this.uf, 2);
            Endereco end = new Endereco();
            end.setIdEndereco(0);
            end.setCep(this.cep);
            end.setRua(this.txtRua.getText());
            end.setNumero(Integer.parseInt(this.txtNumero.getText()));
            end.setComplemento(this.txtComplemento.getText());
            end.setBairro(this.txtBairro.getText());
            end.setCidade(this.txtCidade.getText());
            end.setUf(this.uf);
            Agencia ag = new Agencia();
            ag.setCodBanco(Integer.parseInt(this.txtcodBanco.getText()));
            ag.setCodAgencia(0);
            ag.setEndereco(end);
                AgenciaDAO.inserirAg(ag);
                Agencia a = AgenciaDAO.ultimaAg();
                txtCodAgencia.setText(String.valueOf(a.getCodAgencia()));
                this.painetTab = new JPanel(new BorderLayout());
                super.add(this.painetTab,BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painetTab);
                fb3.addTabelaAg();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "Campo NÚMERO contem espaços em branco!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            

        }
        if(e.getActionCommand() .equals("c")){
            try{
            this.cep = Utilitarios.campoSemCaracteres(this.txtCep.getText());
            this.uf = Utilitarios.campoSemCaracteres(this.txtUf.getText());
            FormularioExcecoes.campoVazio("Campo AGÊNCIA", 
                    this.txtCodAgencia.getText());
            FormularioExcecoes.validarCampoNumerico(
                        this.txtCodAgencia.getText(),"AGÊNCIA");
            Agencia ag = AgenciaDAO.consultaAg(Integer.parseInt(
                    this.txtCodAgencia.getText()));
            this.txtCep.setText(ag.getEndereco().getCep());
            this.txtRua.setText(ag.getEndereco().getRua());
            this.txtNumero.setText(String.valueOf(
                    ag.getEndereco().getNumero()));
            this.txtComplemento.setText(ag.getEndereco().getComplemento());
            this.txtBairro.setText(ag.getEndereco().getBairro());
            this.txtCidade.setText(ag.getEndereco().getCidade());
            this.txtUf.setText(ag.getEndereco().getUf());
            }catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null,
                        "AGÊNCIA não encontrada!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand() .equals("a")){
            try{
            this.cep = Utilitarios.campoSemCaracteres(this.txtCep.getText());
            this.uf = Utilitarios.campoSemCaracteres(this.txtUf.getText());
            FormularioExcecoes.campoVazio("Campo AGÊNCIA", 
                    this.txtCodAgencia.getText());
            FormularioExcecoes.validarCampoNumerico(
                        this.txtCodAgencia.getText(),"AGÊNCIA");
            FormularioExcecoes.campoVazio("Campo CEP", this.cep);
                FormularioExcecoes.campoVazio("Campo RUA",
                        this.txtRua.getText());
                FormularioExcecoes.campoVazio("Campo NÚMERO",
                        this.txtNumero.getText());
                FormularioExcecoes.validarCampoNumerico(
                        this.txtNumero.getText(),"NÚMERO");
                FormularioExcecoes.campoVazio("Campo BAIRRO", 
                        Utilitarios.campoSemCaracteres(
                                this.txtBairro.getText()));
                FormularioExcecoes.campoVazio("Campo CIDADE", 
                        Utilitarios.campoSemCaracteres(
                                this.txtCidade.getText()));
                FormularioExcecoes.campoVazio("Campo UF", this.uf);
                FormularioExcecoes.validarTamanho(
                        "Para o campo UF digite apenas a sigla correspondente:\n"
                       +"Ex.: SP, RJ, CE, entre outros.", this.uf, 2);
            Agencia a = AgenciaDAO.consultaAg(Integer.parseInt(
                    this.txtCodAgencia.getText()));
            Endereco end = new Endereco();
            end.setIdEndereco(a.getEndereco().getIdEndereco());
            end.setCep(this.cep);
            end.setRua(this.txtRua.getText());
            end.setNumero(Integer.parseInt(this.txtNumero.getText()));
            end.setComplemento(this.txtComplemento.getText());
            end.setBairro(this.txtBairro.getText());
            end.setCidade(this.txtCidade.getText());
            end.setUf(this.uf);
            Agencia ag = new Agencia();
            ag.setEndereco(end);
            ag.setCodBanco(Integer.parseInt(this.txtcodBanco.getText()));
            ag.setCodAgencia(Integer.parseInt(this.txtCodAgencia.getText()));
                GenericoDAO.atualizar(ag);
                this.painetTab = new JPanel(new BorderLayout());
                super.add(this.painetTab,BorderLayout.CENTER);
                FabricaComponentes fb3 = new FabricaComponentes(this.painetTab);
                fb3.addTabelaAg();
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "Campo NÚMERO contem espaços em branco!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        if(e.getActionCommand() .equals("e")){
            try{
            this.cep = Utilitarios.campoSemCaracteres(this.txtCep.getText());
            this.uf = Utilitarios.campoSemCaracteres(this.txtUf.getText());
            FormularioExcecoes.campoVazio("Campo AGÊNCIA", 
                    this.txtCodAgencia.getText());
            FormularioExcecoes.validarCampoNumerico(
                        this.txtCodAgencia.getText(),"AGÊNCIA");
            AgenciaDAO.excluirAg(Integer.parseInt(this.txtCodAgencia.getText()));
            this.painetTab = new JPanel(new BorderLayout());
            super.add(this.painetTab,BorderLayout.CENTER);
            FabricaComponentes fb3 = new FabricaComponentes(this.painetTab);
            fb3.addTabelaAg();
            }catch(IndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null,
                        "AGÊNCIA não encontrada!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Excecoes ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Essa AGÊNCIA possui CLIENTES!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand() .equals("lp")){
            this.txtCodAgencia.setText("");
            this.txtCep.setText("");
            this.txtRua.setText("");
            this.txtNumero.setText("");
            this.txtComplemento.setText("");
            this.txtBairro.setText("");
            this.txtCidade.setText("");
            this.txtUf.setText("");
    }

    }
}
