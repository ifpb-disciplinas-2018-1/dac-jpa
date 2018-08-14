package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/08/2018, 07:47:19
 */
@Embeddable
public class CPF implements Serializable {

    //12345678909
    //123.456.789-09
    // ser válido
//    @Column(name = "cpf")
    private String numero;

    public CPF(String numero) {
        this.numero = numero;
    }

    public CPF() {
        this("");
    }

    public String numero() {
        return numero;
    }

    public String formatado() {
        return numero;
    }

    public boolean valido() {
        return true;
    }

}
