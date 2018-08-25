package br.edu.ifpb.main;

import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Projeto;
import br.edu.ifpb.domain.Trabalho;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 16/08/2018, 09:54:36
 */
public class MainTernario {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Endereco endereco = new Endereco(
                "Rua", "bairro", "cidade"
        );
        Projeto dac = new Projeto(
                "DAC"
        );
        Funcionario jose = new Funcionario(
                "Jose", "123", endereco
        );
        Trabalho trabalho = new Trabalho(
                dac, jose, 10
        );

        dac.addFuncionario(trabalho);
        jose.novoProjeto(trabalho);
        
        em.getTransaction().begin();
        em.persist(endereco);
        em.persist(trabalho);
        em.persist(dac);
        em.persist(jose);
        em.getTransaction().commit();
    }

}
