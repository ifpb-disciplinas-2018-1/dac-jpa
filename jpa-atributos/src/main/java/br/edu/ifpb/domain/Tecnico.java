package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/08/2018, 08:14:20
 */
@Entity
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(length = 80, nullable = false)
    private String nome;
    @Column(length = 25)
    private String sigepe;
    @Column(length = 11, nullable = false)
    private String cpf;

    public Tecnico(String nome, String sigepe, String cpf) {
        this.nome = nome;
        this.sigepe = sigepe;
        this.cpf = cpf;
    }

    public Tecnico() {
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

    public String getSigepe() {
        return sigepe;
    }

    public void setSigepe(String sigepe) {
        this.sigepe = sigepe;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.sigepe);
        hash = 37 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tecnico other = (Tecnico) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sigepe, other.sigepe)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }
 
    

}
//CREATE TABLE tecnico
//(
//  codigo serial NOT NULL,
//  nome character varying(80) NOT NULL,
//  sigepe character varying(25),
//  cpf character varying(11) NOT NULL,
//  CONSTRAINT tecnico_pkey PRIMARY KEY (codigo)
//)
