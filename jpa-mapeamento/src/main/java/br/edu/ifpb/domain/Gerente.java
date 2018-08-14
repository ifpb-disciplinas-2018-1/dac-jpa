package br.edu.ifpb.domain;

import br.edu.ifpb.infra.jpa.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/08/2018, 07:33:09
 */
@Entity
public class Gerente implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String cpf;
    @Temporal(TemporalType.DATE)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate inicio;
    @Temporal(TemporalType.DATE)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fim;
    // UM Gerente gerencia UM Departamento
    @OneToOne(mappedBy = "gerente")
    private Departamento departamento; // 1 -> 1 - bidirecional

    // UM Gerente gerencia MUITOS Projetos
    @OneToMany(mappedBy = "gerente")
    private List<Projeto> projetos; // 1 -> N - bidirecional

    public Gerente() {
        this.projetos = new ArrayList<>();
    }

    public Gerente(String nome, String cpf, LocalDate inicio, LocalDate fim, Departamento departamento) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.inicio = inicio;
        this.fim = fim;
        this.departamento = departamento;
    }

    public void novoProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
