package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.DepartamentoGerente;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Funcionario;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/08/2018, 22:51:20
 */
public class MainConsultasJPQL {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

//        consultarTodosOsFuncionarios(em);
//        consultarDepartamentoComId(em);
//        consultarDepartamentoComIdParametros(em);
//        consultarDependentesComPaginacao(em);
//        consultarNomeDoDependentes(em);
//        consultarNomeDoDepartamentoEGerente(em);
//        consultarNomeDoDepartamentoEGerenteComTipo(em);
//        consultarNomeDoFuncionarioQuePossuiDependentes(em);
//        consultarNomeDoFuncionarioNomeDoDependenteJOIN(em);
//        consultarNomeDoFuncionarioQuantidadeDeDependentesLEFTJOIN(em);
//        consultarDependentesComIdEntre(em);
//        consultarDependentesComIdFora(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDependentesComIdForaBETWEEN(em);
//        consultarDepartamentoSemGerente(em);
//        consultarDepartamentoComGerente(em);
//        consultarFuncionarioPossuiDependente(em);
//        consultarFuncionarioDependenteIniciandoComLetra(em);
//        consultarPrimeiraLetraNomesDosDependente(em);
//        consultarNumeroDeTodosOsDependentes(em);
//        consultarNomeDoFuncionarioEQuantidadeDependentes(em);
//        consultarDependenteComIdSuperiorAMedia(em);
//        consultarDependenteSeTodosIdSuperiorADez(em);
//        consultarDependenteSeQualquerIdSuperiorADez(em);
        atualizarNomeTodosDependentes(em);
//        removerDependenteComId(em);
//        consultarTodosOsDependentesNamedQuery(em);
//        consultarOsDependentesComIdNamedQuery(em);
//        consultarTodosOsFuncionariosNativeQuery(em);
//        consultarNomeIdFuncionariosNativeQuery(em);
    }

    private static void consultarTodosOsFuncionarios(EntityManager em) {
        // SELECT * FROM Funcionario
        String jpql = "FROM Funcionario f";
//        String jpql = "SELECT f FROM Funcionario f";
//        Query createQuery = em.createQuery(jpql);
        TypedQuery<Funcionario> query = em
                .createQuery(jpql, Funcionario.class);

//        Funcionario singleResult = query.getSingleResult();
        List<Funcionario> resultList = query.getResultList();

        resultList.forEach(f -> {
            System.out.println(f.getNome());
            System.out.println(f.getEndereco().getBairro());
        }
        );
    }

    private static void consultarDepartamentoComId(EntityManager em) {
        String jpql = "SELECT d FROM Departamento d WHERE d.id=56";
        Departamento departamento = em
                .createQuery(jpql, Departamento.class)
                //                .createQuery("SELECT d FROM Departamento d WHERE d.id", Departamento.class)
                .getSingleResult();
        System.out.println(departamento.getAbreviacao());
        System.out.println(departamento.getGerente().getNome());
    }

    private static void consultarDepartamentoComIdParametros(EntityManager em) {
//        String id = "56 OR 1=1";
//        String jpql = "SELECT d FROM Departamento d WHERE d.id=" + id;
        String jpql = "SELECT d FROM Departamento d WHERE d.id=:id";
//        String jpql = "SELECT d FROM Departamento d WHERE d.id=?1";

        TypedQuery<Departamento> query = em
                .createQuery(jpql, Departamento.class)
                .setParameter("id", 56);
//                .setParameter(1, 56);

        Departamento departamento = query
                .getSingleResult();

        System.out.print(departamento.getAbreviacao());
        System.out.println(" - " + departamento.getGerente().getNome());
    }

    private static void consultarDependentesComPaginacao(EntityManager em) {
//        SELECT CODIGO AS a1, NOME AS a2 FROM DEPENDENTE LIMIT ? OFFSET ?
        String jpql = "SELECT d FROM Dependente d ORDER BY d.codigo";
        Long total = em
                .createQuery("SELECT COUNT(d) FROM Dependente d", Long.class)
                .getSingleResult();
        int dependentePorPagina = 2;
        int inicio = 0;

//        while (inicio < total) {
//            System.out.println("Página: " + ((inicio / dependentePorPagina) + 1));
//            em.createQuery(jpql, Dependente.class)
//                    .setFirstResult(inicio)
//                    .setMaxResults(dependentePorPagina)
//                    .getResultList()
//                    .forEach(d -> System.out.println("\t"+d.getNome()));
//            inicio += dependentePorPagina;
//        }
        // Ir à uma página
//        int pagina = 1;
//        em.createQuery(jpql, Dependente.class)
//                .setFirstResult((pagina - 1) * dependentePorPagina)
//                .setMaxResults(dependentePorPagina)
//                .getResultList()
//                .forEach(d -> System.out.println("\t" + d.getNome()));
    }

    private static void consultarNomeDoDependentes(EntityManager em) {
        // SELECT nome FROM Dependente
        String jpql = "SELECT d.nome FROM Dependente d ORDER BY d.codigo";
        em.createQuery(jpql, String.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void consultarNomeDoDepartamentoEGerente(EntityManager em) {
        String jpql = "SELECT d.abreviacao, d.gerente FROM Departamento d";
        //List<Object[]>, onde array[0] representa o nome, e array[1] gerente
        List<Object[]> resultList = em.createQuery(jpql)
                .getResultList();
        resultList.forEach(tupla -> System.out.println(tupla[0] + " - " + tupla[1]));

//        List<DepartamentoGerente> collect = resultList.stream()
//                .map(
//                        (Object[] t)
//                        -> new DepartamentoGerente((String) t[0], (Gerente) t[1])
//                )
//                .peek(System.out::println)
//                .collect(Collectors.toList());
    }

    private static void consultarNomeDoDepartamentoEGerenteComTipo(EntityManager em) {
        // nome da classe precisa ser totalmente qualificado
        String jpql = "SELECT new br.edu.ifpb.domain.DepartamentoGerente(d.abreviacao, d.gerente) FROM Departamento d";
        List<DepartamentoGerente> resultList = em.createQuery(jpql, DepartamentoGerente.class)
                .getResultList();
        resultList.forEach(System.out::println);
    }

    private static void consultarNomeDoFuncionarioQuePossuiDependentes(EntityManager em) {
        String jpql = "SELECT DISTINCT(f.nome) FROM Funcionario f, IN (f.dependentes) d ";

        em.createQuery(jpql, String.class)
                .getResultList()
                .forEach(System.out::println);
    }

    private static void consultarNomeDoFuncionarioNomeDoDependenteJOIN(EntityManager em) {
        String jpql = "SELECT f.nome,d.nome FROM Funcionario f JOIN f.dependentes d ";
//        String jpql = "SELECT f.nome,d.nome FROM Funcionario f JOIN Dependente d ";

        List<Object[]> resultList = em.createQuery(jpql)
                .getResultList();

        resultList.forEach(tupla -> System.out.println(tupla[0] + " - " + tupla[1]));
    }

    private static void consultarNomeDoFuncionarioQuantidadeDeDependentesLEFTJOIN(EntityManager em) {
        String jpql = "SELECT f.nome, COUNT(d) FROM Funcionario f LEFT JOIN f.dependentes d GROUP BY f.nome ";
// RIGHT OUTER JOIN
        List<Object[]> resultList = em.createQuery(jpql)
                .getResultList();

        resultList.forEach(tupla -> System.out.println(tupla[0] + " - " + tupla[1]));
    }

    private static void consultarDependentesComIdEntre(EntityManager em) {
        // os Dependentes que possuem id entre 200 e 300
        String jpql = " SELECT d FROM Dependente d WHERE d.codigo<=300 AND d.codigo>=200";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDependentesComIdFora(EntityManager em) {
        // os Dependentes que possuem id entre 200 e 300
        String jpql = " SELECT d FROM Dependente d WHERE NOT (d.codigo<=300 AND d.codigo>=200)";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDependentesComIdEntreBETWEEN(EntityManager em) {
        // os Dependentes que possuem id entre 200 e 300
        String jpql = " SELECT d FROM Dependente d WHERE d.codigo BETWEEN (198+4) AND 300";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDependentesComIdForaBETWEEN(EntityManager em) {
        // os Dependentes que estão fora do intervalo entre 200 e 300
        String jpql = " SELECT d FROM Dependente d WHERE d.codigo NOT BETWEEN 200 AND 300";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDepartamentoSemGerente(EntityManager em) {
        //select * from departamento where gerente_id is null
        String jpql = "SELECT d FROM Departamento d WHERE d.gerente IS NULL";
        List<Departamento> resultList = em.createQuery(jpql, Departamento.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getAbreviacao()));
    }

    private static void consultarDepartamentoComGerente(EntityManager em) {
        String jpql = "SELECT d FROM Departamento d WHERE d.gerente IS NOT NULL";
        List<Departamento> resultList = em.createQuery(jpql, Departamento.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getAbreviacao()));
    }

    private static void consultarFuncionarioPossuiDependente(EntityManager em) {
        String jpql = "SELECT f FROM Funcionario f WHERE f.dependentes IS NOT EMPTY";
        List<Funcionario> resultList = em.createQuery(jpql, Funcionario.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarFuncionarioDependenteIniciandoComLetra(EntityManager em) {
        String jpql = "SELECT UPPER(f.nome) FROM Funcionario f, Dependente d "
                + " WHERE d MEMBER OF f.dependentes AND LOWER(d.nome) LIKE :letra";
        List<String> resultList = em.createQuery(jpql, String.class)
                .setParameter("letra", "m%")
                .getResultList();
        resultList.forEach(d -> System.out.println(d));

    }

    private static void consultarPrimeiraLetraNomesDosDependente(EntityManager em) {
        String jpql = "SELECT SUBSTRING(d.nome,1,1) FROM Dependente d";
        List<String> resultList = em.createQuery(jpql, String.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d));
    }

    private static void consultarNumeroDeTodosOsDependentes(EntityManager em) {
        String jpql = "SELECT COUNT(d) FROM Dependente d";
        Long resultList = em.createQuery(jpql, Long.class)
                .getSingleResult();
        System.out.println("resultList = " + resultList);

    }

    private static void consultarNomeDoFuncionarioEQuantidadeDependentes(EntityManager em) {
        // retornar o nome do funcionario e a quantidade de dependentes se a quantidade for maior que 2
//        String jpql = "SELECT f.nome, COUNT(d) AS quantidade FROM Funcionario f, Dependente d"
//                + " WHERE d MEMBER OF f.dependentes AND quantidade > 2";
        String jpql = "SELECT f.nome, COUNT(d) AS quantidade FROM Funcionario f LEFT JOIN f.dependentes d"
                + " GROUP BY f.nome HAVING quantidade > 2  ";
        List<Object[]> resultList = em.createQuery(jpql, Object[].class)
                .getResultList();
        resultList.forEach(d -> System.out.println(Arrays.toString(d)));
    }

    private static void consultarDependenteComIdSuperiorAMedia(EntityManager em) {
//        String jpql2 = " SELECT AVG(d.id) FROM Dependente d";
        String jpql = " SELECT d FROM Dependente d WHERE d.codigo > "
                + " (SELECT AVG(dep.codigo) FROM Dependente dep)";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDependenteSeTodosIdSuperiorADez(EntityManager em) {
        String jpql = "SELECT d FROM Dependente d "
                + " WHERE  10 < ALL "
                + " (SELECT dep.codigo FROM Dependente dep)";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarDependenteSeQualquerIdSuperiorADez(EntityManager em) {
        String jpql = "SELECT d FROM Dependente d "
                + " WHERE  10 < SOME "
                + " (SELECT dep.codigo FROM Dependente dep)";
        List<Dependente> resultList = em.createQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void atualizarNomeTodosDependentes(EntityManager em) {
        String jpql = "UPDATE Dependente d SET d.nome = CONCAT(d.nome,'s') WHERE d.codigo = 202";
        em.getTransaction().begin();
        int resposta = em.createQuery(jpql).executeUpdate();
        em.getTransaction().commit();
        System.out.println("resposta = " + resposta);
    }

    private static void removerDependenteComId(EntityManager em) {
//        String jpql = "DELETE FROM Funcionario WHERE id = 1";
        String jpql = "DELETE FROM Dependente WHERE codigo = 201";
        em.getTransaction().begin();
        int resposta = em.createQuery(jpql).executeUpdate();
        em.getTransaction().commit();
        System.out.println("resposta = " + resposta);
    }

    private static void consultarTodosOsDependentesNamedQuery(EntityManager em) {
        String jpql = "Dependente.todos";
        List<Dependente> resultList = em.createNamedQuery(jpql, Dependente.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarOsDependentesComIdNamedQuery(EntityManager em) {
        String jpql = "Dependente.id";
        List<Dependente> resultList = em.createNamedQuery(jpql, Dependente.class)
                .setParameter("id", 1)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarTodosOsFuncionariosNativeQuery(EntityManager em) {
        String jpql = "SELECT * FROM Funcionario";
         List<Funcionario> resultList = em.createNativeQuery(jpql, Funcionario.class)
                .getResultList();
        resultList.forEach(d -> System.out.println(d.getNome()));
    }

    private static void consultarNomeIdFuncionariosNativeQuery(EntityManager em) {
       String jpql = "SELECT nome, id FROM Funcionario WHERE id=?";
        List<Object[]> resultList = em.createNativeQuery(jpql)
                .setParameter(1, 1)
                .getResultList();
        resultList.forEach(d -> System.out.println(Arrays.toString(d)));
    }

}
