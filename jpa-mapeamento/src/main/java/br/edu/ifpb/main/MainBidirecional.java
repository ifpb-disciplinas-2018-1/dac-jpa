package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Gerente;
import br.edu.ifpb.domain.Projeto;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 08:11:10
 */
public class MainBidirecional {

    public static void main(String[] args) {
        EntityManager manager
                = Persistence.createEntityManagerFactory("ExemploPU")
                        .createEntityManager();
        Departamento departamento = new Departamento(
                "Uninfo"
        );

        // Atribuição no construtor
        Gerente gerente = new Gerente(
                "Job", "123",
                LocalDate.of(2015, 10, 28),
                LocalDate.of(2018, 12, 28),
                departamento 
        );

        departamento.setGerente(gerente);

        Projeto dac = new Projeto("DAC");
        Projeto pos = new Projeto("POS");

        // Atribuição via método set
        gerente.novoProjeto(pos);
        gerente.novoProjeto(dac);
        pos.setGerente(gerente);
        dac.setGerente(gerente);

        Endereco endereco = new Endereco(
                "Rua", "Bairro", "Cidade"
        );
        Funcionario job = new Funcionario(
                "Job", "123", endereco
        );
        
        // Atribuição diretamente no método
        job.novoProjeto(pos);
        job.novoProjeto(dac);
        
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.persist(job);
        manager.persist(pos);
        manager.persist(dac);
        manager.persist(gerente);
        manager.persist(departamento);
        manager.getTransaction().commit();
//        Departamento find = manager.find(Departamento.class, 2);
//        System.out.println(
//                "Departamento: " + find.getAbreviacao()
//        );
//        System.out.println(
//                "Gerente: " + find.getGerente().getNome()
//        );

    }
}
