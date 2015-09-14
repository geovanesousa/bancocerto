package controle.visao;

import controle.GenericoDAO;
import controle.Utilitarios;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Administrador;
import modelo.Agencia;
import modelo.Cliente;

public class FabricaComponentes {

    private Container container;
    private GridBagConstraints cordenadas;
    private JPanel painelTab;

    public FabricaComponentes(JPanel painel) {
        this.container = painel;
        this.cordenadas = new GridBagConstraints();
        this.cordenadas.fill = GridBagConstraints.BOTH;
        Insets espacamento = new Insets(5, 5, 0, 5);
        this.cordenadas.insets = espacamento;
        this.painelTab = painel;
    }

    public JMenuBar addMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        this.container.add(menuBar, BorderLayout.NORTH);
        return menuBar;
    }

    public JMenu addMenu(JMenuBar menuBar, String titulo) {

        JMenu subMenu = new JMenu(titulo);
        menuBar.add(subMenu);
        return subMenu;
    }

    public JMenuItem addMenuItem(JMenu subMenu, String titulo) {

        JMenuItem menuItem = new JMenuItem(titulo);
        subMenu.add(menuItem);
        return menuItem;
    }

    public JLabel addLabel(String titulo, Integer colunaX, Integer LinhaY) {

        JLabel label = new JLabel(titulo);

        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = LinhaY;
        this.container.add(label, this.cordenadas);
        return label;
    }

    public JTextField addTextField(Integer tamanho, Integer colunaX, Integer LinhaY) {

        JTextField txt = new JTextField(tamanho);
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = LinhaY;
        this.cordenadas.gridwidth = 4;
        this.container.add(txt, this.cordenadas);
        return txt;
    }

    public JFormattedTextField addtxtFormatado(String formato, Integer tamanho,
            Integer colunaX, Integer linhaY) {

        JFormattedTextField txt = new JFormattedTextField();
        try {
            txt.setFormatterFactory(
                    new javax.swing.text.DefaultFormatterFactory(
                            new javax.swing.text.MaskFormatter(formato)));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt.setColumns(tamanho);
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.cordenadas.gridwidth = 4;
        this.container.add(txt, this.cordenadas);

        return txt;

    }

    public JFormattedTextField addtxtNumerico(Integer tamanho,
            Integer colunaX, Integer linhaY) {

        JFormattedTextField txt = new JFormattedTextField();
        txt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new javax.swing.text.NumberFormatter(
                        new java.text.DecimalFormat("#0.00"))));

        txt.setColumns(tamanho);
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.cordenadas.gridwidth = 4;
        this.container.add(txt, this.cordenadas);

        return txt;

    }

    public JPasswordField addCampoSenha(Integer tamanho,
            Integer colunaX, Integer linhaY) {

        JPasswordField txtSenha = new JPasswordField(tamanho);
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.cordenadas.gridwidth = 4;
        this.container.add(txtSenha, this.cordenadas);
        return txtSenha;

    }

    public JButton addBotao(String titulo, Integer colunaX, Integer linhaY) {
        JButton botao = new JButton(titulo);
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.cordenadas.gridwidth = 1;
        this.container.add(botao, this.cordenadas);
        return botao;
    }

    public JComboBox addCbAgencia(Integer colunaX, Integer linhaY,
            List<Agencia> lista) {

        JComboBox cb = new JComboBox();
        cb.addItem("");
        for (Agencia a : lista) {
            cb.addItem(a.getCodAgencia());
        }
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.container.add(cb, this.cordenadas);
        return cb;
    }

    public JComboBox addCbUsuarios(Integer colunaX, Integer linhaY) {

        JComboBox cb = new JComboBox();
        cb.addItem("");
        cb.addItem("Administrador");
        cb.addItem("Cliente");
        this.cordenadas.gridx = colunaX;
        this.cordenadas.gridy = linhaY;
        this.cordenadas.gridwidth = 4;
        this.container.add(cb, this.cordenadas);
        return cb;
    }

    public void addTabelaAdm() {

        this.painelTab.setVisible(false);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CPF");
        modelo.addColumn("Nome");
        modelo.addColumn("Data Nasc.");
        modelo.addColumn("Email");
        List<Administrador> lista = GenericoDAO.listarTodos(Administrador.class);
        for (Administrador a : lista) {
            modelo.addRow(new Object[]{a.getIdAdministrador(),
                a.getPessoa().getNome(),
                Utilitarios.dataParaString(a.getPessoa().getDataNascimento()),
                a.getEmail()});
        }
        JTable tb = new JTable(modelo);
        tb.getColumnModel().getColumn(0).setPreferredWidth(20);
        tb.getColumnModel().getColumn(1).setPreferredWidth(40);
        tb.getColumnModel().getColumn(2).setPreferredWidth(10);
        tb.getColumnModel().getColumn(3).setPreferredWidth(40);
        JScrollPane barraRolagem = new JScrollPane(tb);
        this.painelTab.add(barraRolagem, BorderLayout.CENTER);
        this.painelTab.setVisible(true);
        this.painelTab.updateUI();
    }

    public void addTabelaAg() {

        this.painelTab.setVisible(false);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Ag.");
        modelo.addColumn("CEP");
        modelo.addColumn("Rua");
        modelo.addColumn("Núm.");
        modelo.addColumn("Compl.");
        modelo.addColumn("Bairro");
        modelo.addColumn("Cidade");
        modelo.addColumn("UF");
        List<Agencia> lista = GenericoDAO.listarTodos(Agencia.class);
        for (Agencia a : lista) {
            modelo.addRow(new Object[]{a.getCodAgencia(),
                a.getEndereco().getCep(), a.getEndereco().getRua(),
                a.getEndereco().getNumero(), a.getEndereco().getComplemento(),
                a.getEndereco().getBairro(), a.getEndereco().getCidade(),
                a.getEndereco().getUf()});
        }
        JTable tb = new JTable(modelo);
        JScrollPane barraRolagem = new JScrollPane(tb);
        this.painelTab.add(barraRolagem, BorderLayout.CENTER);
        this.painelTab.setVisible(true);
        this.painelTab.updateUI();
    }

    public void addTabelaCliente() {

        this.painelTab.setVisible(false);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CPF");
        modelo.addColumn("Nome");
        modelo.addColumn("Data Nasc.");
        modelo.addColumn("Ag.");
        modelo.addColumn("Conta");
        modelo.addColumn("CEP");
        modelo.addColumn("Rua");
        modelo.addColumn("Núm.");
        modelo.addColumn("Compl.");
        modelo.addColumn("Bairro");
        modelo.addColumn("Cidade");
        modelo.addColumn("UF");
        List<Cliente> lista = GenericoDAO.listarTodos(Cliente.class);
        for (Cliente a : lista) {
            modelo.addRow(new Object[]{a.getIdCliente(), a.getPessoa().getNome(),
                Utilitarios.dataParaString(a.getPessoa().getDataNascimento()),
                a.getAgencia().getCodAgencia(), a.getConta().getNumConta(),
                a.getEndereco().getCep(), a.getEndereco().getRua(),
                a.getEndereco().getNumero(), a.getEndereco().getComplemento(),
                a.getEndereco().getBairro(), a.getEndereco().getCidade(),
                a.getEndereco().getUf()});
        }
        JTable tb = new JTable(modelo);
        JScrollPane barraRolagem = new JScrollPane(tb);
        this.painelTab.add(barraRolagem, BorderLayout.CENTER);
        this.painelTab.setVisible(true);
        this.painelTab.updateUI();
    }

    public void addPainel(JPanel painel) {

        this.container.add(painel);

    }
}
