package br.edu.ifpb.domain.hotel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/08/2018, 16:51:49
 */
public class ReservaId implements Serializable {

    private int idHotel;
    private int idHospede;

    public ReservaId() {
    }

    public ReservaId(int idHotel, int idHospede) {
        this.idHotel = idHotel;
        this.idHospede = idHospede;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idHotel;
        hash = 67 * hash + this.idHospede;
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
        final ReservaId other = (ReservaId) obj;
        if (this.idHotel != other.idHotel) {
            return false;
        }
        if (this.idHospede != other.idHospede) {
            return false;
        }
        return true;
    }

}
