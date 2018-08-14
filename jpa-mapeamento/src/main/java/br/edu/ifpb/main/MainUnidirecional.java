package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Projeto;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 09/08/2018, 08:11:10
 */
public class MainUnidirecional {

    public static void main(String[] args) {
        EntityManager manager
                = Persistence.createEntityManagerFactory("ExemploPU")
                        .createEntityManager();
        Dependente chaves = new Dependente(
                "Chaves"
        );
        Dependente kiko = new Dependente(
                "Kiko"
        );
        Endereco endereco = new Endereco(
                "Rua", "bairro", "Cz"
        );
        Funcionario madruga = new Funcionario(
                "Madruga", "123", endereco
        );
        // Problemas com unicidade
//        Funcionario florinda = new Funcionario(
//                "Florinda", "124", endereco
//        );
        Departamento departamento = new Departamento(
                "UNINFO"
        );

        Projeto dac = new Projeto("DAC");
        Projeto pdm = new Projeto("PDM");

        madruga.novoDependente(chaves);
        madruga.novoDependente(kiko);
        madruga.setDepartamento(departamento);

        madruga.novoProjeto(pdm);
        madruga.novoProjeto(dac);
        /*
            During synchronization a new object was 
            found through a relationship that was 
            not marked cascade PERSIST: 
            br.edu.ifpb.domain.Endereco@78054f54.
         */
        // Quem vai ficar com a chave estrangeira?
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.persist(kiko);
        manager.persist(chaves);
        manager.persist(departamento);
        manager.persist(dac);
        manager.persist(pdm);
        manager.persist(madruga);
//        manager.persist(florinda);
        manager.getTransaction().commit();
    }
}
