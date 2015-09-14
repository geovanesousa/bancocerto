package controle.excecoes;

import controle.GenericoDAO;
import controle.Utilitarios;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agencia;
import modelo.Excecoes;

public class FormularioExcecoes {
    
    public static void campoVazio(String nome,String conteudo) throws Excecoes{
        if(conteudo.isEmpty()){
            throw new Excecoes(nome+" Obrigatório!");
        }
    }
    
    public static void validarSenha(String senha,String senha2,String msgErro) throws Excecoes{
        
        if(!senha.equals(senha2)){
                    
                    throw new Excecoes(msgErro);
                }
        
    }
    public static void validarCampoNumerico(String conteudo,String erroMsg)
            throws Excecoes{
        try{
        String campo = Utilitarios.campoSemCaracteres(conteudo);
        Integer a = Integer.parseInt(campo);
        }catch(NumberFormatException ex){
            throw new Excecoes("Campo "+erroMsg+" deve ser NUMÉRICO!");
        }
    }
    
    public static String validarReal(String conteudo,String erroMsg)
            throws Excecoes{
        try{
        String campo = conteudo.replace(",", ".").trim();
        Double a = Double.parseDouble(campo);
        return campo;
    }catch(NumberFormatException ex){
            throw new Excecoes("Campo "+erroMsg+" deve ser NUMÉRICO!");
    }
    }
    public static void verSeExisteAg() throws IndexOutOfBoundsException{
        List lista = GenericoDAO.listarTodos(Agencia.class);
        lista.get(0);
    }
    public static void validarTamanho(String erroMsg, String conteudo,
            Integer tamanho)throws Excecoes{
        if(conteudo.length()!=tamanho){
            throw new Excecoes(erroMsg);
        }
    }

    
}
