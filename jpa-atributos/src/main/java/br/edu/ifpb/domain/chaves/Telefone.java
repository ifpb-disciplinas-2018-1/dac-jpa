package br.edu.ifpb.domain.chaves;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/08/2018, 08:43:08
 */
@Entity
@IdClass(TelefoneClassId.class)
public class Telefone implements Serializable {

//    @EmbeddedId
//    private TelefonePK chave;
    private String pais;
    @Id
    private String ddd;
    @Id
    private String numero;

    public Telefone() {
    }

    public Telefone(String ddd, String numero) {
//        this.chave = new TelefonePK(ddd, numero);
        this.ddd = ddd;
        this.numero = numero;
        this.pais = "Brasil";
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
//    public TelefonePK getChave() {
//        return chave;
//    }
//
//    public void setChave(TelefonePK chave) {
//        this.chave = chave;
//    }

}
