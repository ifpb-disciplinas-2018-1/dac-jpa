package br.edu.ifpb.domain.mapped;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:52:56
 */
@Entity
public class TAE extends Tecnico {

    private String matricula;

    public TAE(String matricula, String cpf, String nome) {
        super(cpf, nome);
        this.matricula = matricula;
    }

    public TAE() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
