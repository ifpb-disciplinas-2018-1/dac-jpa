package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/08/2018, 07:34:38
 */
@Entity
@SequenceGenerator(
        name = "seq_name",
        allocationSize = 5,
        initialValue = 2,
        sequenceName = "minha_seq"
)
public class Aluno implements Serializable{

    @Id
    @GeneratedValue(generator = "seq_name",strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 80)
    private String nome;
    private double cre;

    public Aluno() {
    }

    public Aluno(String nome, double cre) {
        this.nome = nome;
        this.cre = cre;
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

    public double getCre() {
        return cre;
    }

    public void setCre(double cre) {
        this.cre = cre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.nome);
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.cre) ^ (Double.doubleToLongBits(this.cre) >>> 32));
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
        final Aluno other = (Aluno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.cre) != Double.doubleToLongBits(other.cre)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
}
