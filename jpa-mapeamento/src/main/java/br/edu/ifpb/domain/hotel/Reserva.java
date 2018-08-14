package br.edu.ifpb.domain.hotel;

import br.edu.ifpb.infra.jpa.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/08/2018, 16:46:49
 */
@Entity
@IdClass(ReservaId.class)
public class Reserva implements Serializable {

    @Id
    @Column(name = "id_hotel", insertable = false, updatable = false)
    private int idHotel;
    @Id
    @Column(name = "id_hospede", insertable = false, updatable = false)
    private int idHospede;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;
    @Temporal(TemporalType.DATE)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataDaReserva;

    public Reserva() {
    }

    public Reserva(Hotel hotel, Hospede hospede, LocalDate dataDaReserva) {
        this.hotel = hotel;
        this.hospede = hospede;
        this.dataDaReserva = dataDaReserva;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public LocalDate getDataDaReserva() {
        return dataDaReserva;
    }

    public void setDataDaReserva(LocalDate dataDaReserva) {
        this.dataDaReserva = dataDaReserva;
    }

}
