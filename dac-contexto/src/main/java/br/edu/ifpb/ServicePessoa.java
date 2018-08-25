package br.edu.ifpb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 16/08/2018, 08:08:16
 */
@Stateless
//@Stateful
public class ServicePessoa {

//    @PersistenceContext(
//            type = PersistenceContextType.EXTENDED
//    )
    @PersistenceContext
    private EntityManager em;

    public void salvar(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public void remover(Pessoa pessoa) {
//        Pessoa pessoa2 = em.find(Pessoa.class, pessoa.getId());
//        Pessoa pessoa2 = em.getReference(Pessoa.class, pessoa.getId());
//        em.remove(pessoa2);

        em.remove(
                em.merge(
                        pessoa
                )
        );
    }

    public void atualizar(Pessoa pessoa) {
        System.out.println(pessoa.getId());
        em.merge(pessoa);
        System.out.println(pessoa.getId());
    }

    public List<Pessoa> listarTodas() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                .getResultList();
    }

//    @Remove
    public void atualizarPessoa(Pessoa pessoa) {
        pessoa.setNome("Maria");
    }

}
