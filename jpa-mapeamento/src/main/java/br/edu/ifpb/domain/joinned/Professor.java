package br.edu.ifpb.domain.joinned;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:25:23
 */
@Entity
@DiscriminatorValue("2")
public class Professor extends Pessoa {

    private String siape;

    public Professor(String siape, String cpf, String nome) {
        super(cpf, nome);
        this.siape = siape;
    }

    public Professor() {
        super();
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

}
