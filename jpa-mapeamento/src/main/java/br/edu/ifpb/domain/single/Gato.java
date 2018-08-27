package br.edu.ifpb.domain.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 07:37:51
 */
@Entity
@DiscriminatorValue("G")
public class Gato extends Animal {

    private String manha;

    public Gato() {
        super();
    }

    public Gato(String manha, String nome, String raca) {
        super(nome, raca);
        this.manha = manha;
    }

    public String getManha() {
        return manha;
    }

    public void setManha(String manha) {
        this.manha = manha;
    }

}
