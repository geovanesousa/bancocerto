package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Agencia implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codAgencia;
    
    @Column(nullable = false)
    private Integer codBanco;
    
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Endereco endereco;

    public Integer getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(Integer codAgencia) {
        this.codAgencia = codAgencia;
    }

    public Integer getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Integer codBanco) {
        this.codBanco = codBanco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
}
