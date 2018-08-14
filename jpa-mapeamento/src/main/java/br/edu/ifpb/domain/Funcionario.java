package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 08:04:50
 */
@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String cpf;

    // UM Funcionario tem UM Endereco
    @OneToOne
//    @JoinColumn(name = "end_cod")
    @JoinColumn(name = "end_cod", unique = true)
    private Endereco endereco; // 1 -> 1 - unidirecional

    // UM Funcionario tem MUITOS Dependentes
    @OneToMany
    @JoinColumn(name = "func_id")
    private List<Dependente> dependentes; // 1 -> N - unidirecional

    // MUITOS Funcionarios participam de UM Departamento
    @ManyToOne
    private Departamento departamento; // N -> 1 - unidirecional

    // MUITOS Funcionarios trabalham em MUITOS Projetos
    @ManyToMany(mappedBy = "funcionarios")
    private List<Projeto> projetos; // N -> N - bidirecional

    public Funcionario() {
        this.dependentes = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }

    public Funcionario(String nome, String cpf, Endereco endereco) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public void novoDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }

    public void novoProjeto(Projeto projeto) {
        this.projetos.add(projeto);
        projeto.addFuncionario(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
//@JoinTable(name = "Trabalho",
//            joinColumns = @JoinColumn(name = "func_id"),
//            inverseJoinColumns = @JoinColumn(name = "proj_id"))