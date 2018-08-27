package br.edu.ifpb.domain.mapped;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:52:56
 */
@Entity
public class Analista extends Tecnico{

    private String siape;

    public Analista(String siape, String cpf, String nome) {
        super(cpf, nome);
        this.siape = siape;
    }

    public Analista() {
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }
    
}
