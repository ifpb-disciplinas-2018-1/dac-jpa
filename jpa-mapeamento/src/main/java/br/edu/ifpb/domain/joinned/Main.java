package br.edu.ifpb.domain.joinned;

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

        Pessoa pessoa = new Pessoa(
                "123", "Chaves"
        );

        Aluno aluno = new Aluno(
                "123", "1324", "Godiles"
        );

        Professor professor = new Professor(
                "134", "143", "Madruga"
        );

        em.getTransaction().begin();
//        em.persist(pessoa);
        em.persist(aluno);
        em.persist(professor);
        em.getTransaction().commit();

//        em.createQuery("FROM Pessoa p", Pessoa.class)
//                .getResultList()
//                .forEach(System.out::println);
    }

}
