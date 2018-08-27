package br.edu.ifpb.domain.table;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2018, 07:43:23
 */
public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();
        Carro carro = new Carro(
                "Rex", "F10"
        );

        Celta celta = new Celta(
                "2009", "Celtinha", "massa"
        );

        Fusca fusca = new Fusca(
                "123", "meu fusca", "86"
        );

        em.getTransaction().begin();
        em.persist(carro);
        em.persist(celta);
        em.persist(fusca);
        em.getTransaction().commit();
        
//        em.createQuery("FROM Carro c", Carro.class)
//                .getResultList()
//                .forEach(System.out::println);
    }

}
