package br.edu.ifpb.domain.table;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:06:48
 */
@Entity
public class Fusca extends Carro {

    private String numeroDoChassi;

    public Fusca() {
        super();
    }

    public Fusca(String numeroDoChassi, String nome, String modelo) {
        super(nome, modelo);
        this.numeroDoChassi = numeroDoChassi;
    }

    public String getNumeroDoChassi() {
        return numeroDoChassi;
    }

    public void setNumeroDoChassi(String numeroDoChassi) {
        this.numeroDoChassi = numeroDoChassi;
    }

}
