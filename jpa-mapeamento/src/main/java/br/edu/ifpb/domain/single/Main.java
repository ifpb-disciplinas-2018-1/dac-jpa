package br.edu.ifpb.domain.single;

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

        Animal animal = new Animal(
                "Dog", "Vira lata"
        );

        Cachorro cachorro = new Cachorro(
                "pedigree", "Costelinha", "Pinscher"
        );

        Gato gato = new Gato(
                "muita", "baleia", "Sphinx"
        );

        em.getTransaction().begin();
        em.persist(gato);
        em.persist(animal);
        em.persist(cachorro);
        em.getTransaction().commit();
    }

}
