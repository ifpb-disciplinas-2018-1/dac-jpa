package br.edu.ifpb.domain.joinned;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:25:23
 */
@Entity
@DiscriminatorValue("1")
public class Aluno extends Pessoa {

    private String matricula;

    public Aluno(String matricula, String cpf, String nome) {
        super(cpf, nome);
        this.matricula = matricula;
    }

    public Aluno() {
        super();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
