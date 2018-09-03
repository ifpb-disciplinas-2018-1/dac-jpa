package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 16/08/2018, 09:50:43
 */
public class TrabalhoPK implements Serializable {

    private int projetoId;
    private int funcionarioId;

    public TrabalhoPK() {
    }

    public TrabalhoPK(int projetoId, int funcionarioId) {
        this.projetoId = projetoId;
        this.funcionarioId = funcionarioId;
    }

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.projetoId;
        hash = 53 * hash + this.funcionarioId;
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
        final TrabalhoPK other = (TrabalhoPK) obj;
        if (this.projetoId != other.projetoId) {
            return false;
        }
        if (this.funcionarioId != other.funcionarioId) {
            return false;
        }
        return true;
    }

}
