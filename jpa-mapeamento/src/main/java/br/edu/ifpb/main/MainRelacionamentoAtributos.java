package br.edu.ifpb.main;

import br.edu.ifpb.domain.hotel.Hospede;
import br.edu.ifpb.domain.hotel.Hotel;
import br.edu.ifpb.domain.hotel.Reserva;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 08:11:10
 */
public class MainRelacionamentoAtributos {

    public static void main(String[] args) {
        EntityManager manager
                = Persistence.createEntityManagerFactory("ExemploPU")
                        .createEntityManager();

        Hotel hotel = new Hotel(
                "Hotel Transilvania"
        );

        Hospede dracula = new Hospede(
                "Dracula"
        );
        Hospede mt = new Hospede(
                "Mt"
        );

        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);

        Reserva reserva = new Reserva(
                hotel, dracula, hoje
        );
        Reserva segunda = new Reserva(
                hotel, mt, amanha
        );
        dracula.novaReserva(reserva);
        hotel.novoHospede(reserva);
        hotel.novoHospede(segunda);
        mt.novaReserva(segunda);

        manager.getTransaction().begin();

        manager.persist(reserva);
        manager.persist(segunda);
        manager.persist(mt);
        manager.persist(hotel);
        manager.persist(dracula);
        manager.getTransaction().commit();
    }
}
