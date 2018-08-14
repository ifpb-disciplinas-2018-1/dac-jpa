package br.edu.ifpb.domain.hotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/08/2018, 16:32:05
 */
@Entity
public class Hotel implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String abreviacao;
//    @ManyToMany(mappedBy = "reservas")
    @OneToMany(mappedBy = "hotel")
    private List<Reserva> hospedes;

    public Hotel(String abreviacao) {
        this();
        this.abreviacao = abreviacao;
    }

    public Hotel() {
        this.hospedes = new ArrayList<>();
    }

    public void novoHospede(Reserva reserva) {

        this.hospedes.add(reserva);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public List<Reserva> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Reserva> hospedes) {
        this.hospedes = hospedes;
    }

}
