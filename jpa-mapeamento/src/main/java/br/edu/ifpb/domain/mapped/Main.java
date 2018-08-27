package br.edu.ifpb.domain.mapped;

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

//        Tecnico tecnico = new Tecnico(
//                "123", "Cuca"
//        );

        Analista analista = new Analista(
                "123", "1233", "Chiquinha"
        );
        TAE tae = new TAE(
                "145", "145", "Doug"
        );

//        em.getTransaction().begin();
//        em.persist(analista);
//        em.persist(tae);
//        em.getTransaction().commit();
        
        em.createQuery("FROM Tecnico t", Tecnico.class)
                .getResultList()
                .forEach(System.out::println);
    }

}
