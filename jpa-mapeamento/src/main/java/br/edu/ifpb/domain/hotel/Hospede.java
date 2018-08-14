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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 14/08/2018, 16:32:16
 */
@Entity
public class Hospede implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
//    @ManyToMany
    @OneToMany(mappedBy = "hospede")
    private List<Reserva> reservas;

    public Hospede() {
        this.reservas = new ArrayList<>();
    }

    public Hospede(String nome) {
        this();
        this.nome = nome;
    }

    public void novaReserva(Reserva reserva) {
       
        this.reservas.add(reserva);
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
