package br.edu.ifpb.domain.table;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:06:48
 */
@Entity
public class Celta extends Carro {

    private String ano;

    public Celta() {
        super();
    }

    public Celta(String ano, String nome, String modelo) {
        super(nome, modelo);
        this.ano = ano;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

}
