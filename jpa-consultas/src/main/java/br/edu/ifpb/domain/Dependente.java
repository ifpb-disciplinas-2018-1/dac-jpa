package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 08:32:17
 */
@NamedQueries({
    @NamedQuery(
            name = "Dependente.todos",
            query = "SELECT d FROM Dependente d"
    )
    ,
    @NamedQuery(
            name = "Dependente.id",
            query = "SELECT d FROM Dependente d WHERE d.codigo=:id"
    )}
)
@Entity
public class Dependente implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    private String nome;

    public Dependente() {
    }

    public Dependente(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
