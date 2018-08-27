package br.edu.ifpb.domain.mapped;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 08:24:32
 */
@MappedSuperclass
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String cpf;
    private String nome;

    public Tecnico() {
    }

    public Tecnico(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "id=" + id + ", cpf=" + cpf + ", nome=" + nome + '}';
    }

   

}
