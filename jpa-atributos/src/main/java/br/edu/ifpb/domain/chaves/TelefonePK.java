package br.edu.ifpb.domain.chaves;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/08/2018, 08:44:29
 */
@Embeddable
public class TelefonePK implements Serializable {

    private String ddd;
    private String numero;

    public TelefonePK() {
    }

    public TelefonePK(String dde, String numero) {
        this.ddd = dde;
        this.numero = numero;
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
}
