package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/08/2018, 07:52:51
 */
@Entity
@TableGenerator(
        name = "table_name",
        //        table = "minha_tabela",
        table = "sequence",
        initialValue = 5,
        allocationSize = 3,
        pkColumnValue = "seq_minha_tabela"
)
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(generator = "table_name", strategy = GenerationType.TABLE)
    private int codigo;
    private String profile;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto; // BLOB
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String descricao; //CLOB

    public Perfil() {
    }

    public Perfil(String profile, byte[] foto) {
        this.profile = profile;
        this.foto = foto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codigo;
        hash = 89 * hash + Objects.hashCode(this.profile);
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
        final Perfil other = (Perfil) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        return true;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

}
