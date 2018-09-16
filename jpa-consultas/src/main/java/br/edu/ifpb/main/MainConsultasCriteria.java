package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.DepartamentoGerente;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Funcionario_;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/08/2018, 22:51:20
 */
public class MainConsultasCriteria {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

//        consultarTodosOsFuncionarios(em);
//        consultarDepartamentoComId(em);
//        consultarNomeDoDependentes(em);
//        consultarDependentesComIdMaior(em);
//        consultarAbreviacaoDoDepartamentoEGerente(em);
//        consultarNomeDoDepartamentoEGerenteComConstrutor(em);
//        consultarAQuantidadeDeDependentes(em);
//        consultarNomeDoFuncionarioQuePossuiDependentes(em);
//        consultarNomeDoFuncionarioNomeDoDependenteJOIN(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDepartamentoSemGerente(em);
//        consultarFuncionarioPossuiDependente(em);
//        consultarFuncionarioDependenteIniciandoComLetra(em);
//        consultarNomeDoFuncionarioEQuantidadeDependentes(em);
    }

    private static void consultarTodosOsFuncionarios(EntityManager em) {
        // SELECT f FROM Funcionario f
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteriaQuery = builder.createQuery(Funcionario.class);
        Root<Funcionario> root = criteriaQuery.from(Funcionario.class);
        CriteriaQuery<Funcionario> consulta = criteriaQuery.select(root);
        em.createQuery(consulta)
                .getResultList()
                .forEach(
                        f -> System.out.println(f.getNome())
                );
    }

    private static void consultarDepartamentoComId(EntityManager em) {
        //SELECT d FROM Departamento d WHERE d.id=56
        CriteriaBuilder builder = em.getCriteriaBuilder();
        //o que queremos selecionar
        CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
        // onde faremos a busca. Qual tabela?
        Root<Departamento> root = criteria.from(Departamento.class);
        Predicate equal = builder.equal(
                root.get("id"),
                56
        );
        CriteriaQuery<Departamento> select = criteria
                .select(root)
                .where(equal);

        em.createQuery(select)
                .getResultList()
                .forEach(
                        f -> System.out.println(f.getAbreviacao())
                );
    }

    private static void consultarNomeDoDependentes(EntityManager em) {
        // SELECT d.nome FROM Dependente d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Dependente> root = criteria.from(Dependente.class);
        CriteriaQuery<String> select = criteria.select(root.get("nome"));
        em.createQuery(select)
                .getResultList()
                .forEach(System.out::println);

    }

    private static void consultarDependentesComIdMaior(EntityManager em) {
        // SELECT d FROM Dependente d WHERE d.codigo > 34
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dependente> criteria = builder.createQuery(Dependente.class);
        Root<Dependente> root = criteria.from(Dependente.class);
        Predicate maiorQue = builder.greaterThan(
                root.get("codigo"),
                builder.parameter(Integer.class, "id")
        );
        CriteriaQuery<Dependente> select = criteria.select(root).where(maiorQue);

        em.createQuery(select)
                .setParameter("id", 34)
                .getResultList()
                .forEach(
                        d -> System.out.println(d.getNome())
                );

    }

    private static void consultarAbreviacaoDoDepartamentoEGerente(EntityManager em) {
        // SELECT d.abreviacao, d.gerente FROM Departamento d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DepartamentoGerente> criteria = builder.createQuery(DepartamentoGerente.class);
        Root<Departamento> root = criteria.from(Departamento.class);
        CriteriaQuery<DepartamentoGerente> multiselect = criteria.multiselect(
                root.get("abreviacao"), root.get("gerente")
        );
        em.createQuery(multiselect)
                .getResultList()
                .forEach(
                        f -> System.out.println(
                                f.getAbreviacao() + " " + f.getGerente().getNome()
                        )
                );
    }

    private static void consultarNomeDoDepartamentoEGerenteComConstrutor(EntityManager em) {
        // SELECT new DepartamentoGerente(d.abreviacao, d.gerente) FROM Departamento d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DepartamentoGerente> criteria = builder.createQuery(DepartamentoGerente.class);
        Root<Departamento> root = criteria.from(Departamento.class);
//        CompoundSelection<DepartamentoGerente> construct = builder.construct(
//                DepartamentoGerente.class, root.get("abreviacao"), root.get("gerente")
//        );
//        CriteriaQuery<DepartamentoGerente> multiselect = criteria.select(construct);
        CriteriaQuery<DepartamentoGerente> multiselect = criteria.select(
                builder.construct(
                        DepartamentoGerente.class, root.get("abreviacao"), root.get("gerente")
                )
        );

        em.createQuery(multiselect)
                .getResultList()
                .forEach(
                        f -> System.out.println(
                                f.getAbreviacao() + " " + f.getGerente().getNome()
                        )
                );
    }

    private static void consultarAQuantidadeDeDependentes(EntityManager em) {
        // SELECT COUNT(d) FROM Dependente d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Dependente> root = criteria.from(Dependente.class);
        Expression<Long> count = builder.count(root);
        CriteriaQuery<Long> select = criteria.select(count);
        Long resposta = em.createQuery(select)
                .getSingleResult();
        System.out.println("resposta = " + resposta);
    }

    private static void consultarNomeDoFuncionarioQuePossuiDependentes(EntityManager em) {
        // SELECT f FROM Funcionario f WHERE f.dependentes IS NOT EMPTY
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);
        Expression<Boolean> restriction = builder.isNotEmpty(
                root.get("dependentes")
        );
        CriteriaQuery<Funcionario> select = criteria.select(root).where(restriction);
        em.createQuery(select)
                .getResultList()
                .forEach(
                        f -> System.out.println(f.getNome())
                );

    }

    private static void consultarNomeDoFuncionarioNomeDoDependenteJOIN(EntityManager em) {
        // SELECT f.nome, d.nome FROM Funcionario f JOIN f.dependentes d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Funcionario> root = criteria.from(Funcionario.class);
        Join<Funcionario, Dependente> join = root.join(Funcionario_.dependentes);
        CompoundSelection<Tuple> tuple = builder.tuple(
                join.getParent().get(("nome")).alias("nomeFunc"),
                join.get("nome").alias("nome")
        );
        CriteriaQuery<Tuple> select = criteria.select(tuple);

        em.createQuery(select)
                .getResultList()
                .forEach(
                        t -> System.out.println(t.get("nomeFunc")+" "+t.get("nome"))
                );
    }
}
