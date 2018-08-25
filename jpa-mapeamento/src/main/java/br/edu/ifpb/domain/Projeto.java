package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 09:49:02
 */
@Entity
public class Projeto implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String descricao;

    //MUITOS Projetos são gerenciados por UM Gerente
    @ManyToOne
    private Gerente gerente; // N -> 1 - bidirecional

    //MUITOS Projetos são compostos por MUITOS Funcionarios
    //@ManyToMany
    //private List<Funcionario> funcionarios; // N -> N
    @OneToMany(mappedBy = "projeto")
    private List<Trabalho> funcionarios;
    public Projeto() {
        this.funcionarios = new ArrayList<>();
    }

    public Projeto(String descricao) {
        this();
        this.descricao = descricao;
    }

    public void addFuncionario(Trabalho funcionario){
        this.funcionarios.add(funcionario);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

}
