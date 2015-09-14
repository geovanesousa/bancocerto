package controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Utilitarios {
    
    public static String dataParaString(Date data){
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataTexto = format.format(data);
        return dataTexto;
    }
    
    public static Date stringParaData(String dataNascimento){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date data = Calendar.getInstance().getTime();
        try {
            data = sdf.parse(dataNascimento);
            // se passou pra cá, é porque a data é válida
        } catch (ParseException e) {
            // se cair aqui, a data é inválida
            JOptionPane.showMessageDialog(null,"DATA DE NASCIMENTO INVÁLIDA!","Erro", JOptionPane.ERROR_MESSAGE);
        }

        return data;
    }
    
    public static Double txtParaDouble(String valor){
        
        Double v = Double.parseDouble(valor.replace(",", "."));
        return v;
    }
    public static String campoSemCaracteres(String conteudo){
        String campo = conteudo.replace(".", "").replace("-", "")
                .replace("/", "").replace(" ", "").trim();
        
        return campo;
    }
    
}
