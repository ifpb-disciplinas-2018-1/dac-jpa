package br.edu.ifpb.main;

import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.FuncionarioVO;
import br.edu.ifpb.domain.Funcionario_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 05/09/2018, 09:36:46
 */
public class ExecutorCriteriaQuery {

    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        System.out.println("--------------Listando Todos--------------");
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);
        criteria.select(root);

        TypedQuery<Funcionario> query = em.createQuery(criteria);

        List<Funcionario> empregados = query.getResultList();
        for (Funcionario emp : empregados) {
            imprimeFuncionario(emp);
        }

        System.out.println("--------------Passando Parametros-------------");
        criteria.select(root).where(builder.equal(root.get("id"), 9));
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario emp : empregados) {
            imprimeFuncionario(emp);
        }

        System.out.println("--------------Paginando uma consulta-----------");
        criteria = builder.createQuery(Funcionario.class);
        root = criteria.from(Funcionario.class);
        criteria.select(root);
        query = em.createQuery(criteria);
        query.setMaxResults(2).setFirstResult(1);
        empregados = query.getResultList();

        for (Funcionario emp : empregados) {
            imprimeFuncionario(emp);
        }

//        Predicate condition = builder.equal(root.get("id"), 1);
//        Predicate condition2 = builder.equal(root.get("nome"), "Job");
//        Predicate condition3 = builder.and(condition, condition2);
//        criteria.where(condition3);
        System.out.println("--------------Selecionando apenas o nome-----------");
        CriteriaQuery<String> criteriaString = builder.createQuery(String.class);
        root = criteriaString.from(Funcionario.class);
        criteriaString.select(root.<String>get("nome"))
                .where(builder.equal(root.get("id"), 6));
        TypedQuery<String> queryString = em.createQuery(criteriaString);
//        String nome = queryString.getSingleResult();
//
//        System.out.println("Nome: " + nome);

        System.out.println("--------------Usando o construtor I -----------");
        CriteriaQuery<FuncionarioVO> criteriaVO = builder.createQuery(FuncionarioVO.class);
        Root<Funcionario> emp = criteriaVO.from(Funcionario.class);
        criteriaVO.multiselect(emp.get("nome"), emp.get("id"));
        TypedQuery<FuncionarioVO> queryVO = em.createQuery(criteriaVO);
        List<FuncionarioVO> emps = queryVO.getResultList();
        for (FuncionarioVO empregadoVO : emps) {
            imprimeFuncionarioVO(empregadoVO);
        }

        System.out.println("--------------Usando o construtor II -----------");
        criteriaVO.select(builder.construct(FuncionarioVO.class, emp.get("nome"), emp.get("id")));
        queryVO = em.createQuery(criteriaVO);
        emps = queryVO.getResultList();
        for (FuncionarioVO empregadoVO : emps) {
            imprimeFuncionarioVO(empregadoVO);
        }

        System.out.println("--------------Usando o construtor III e ALIAS-----------");
        CriteriaQuery<Tuple> criteriaTuple = builder.createTupleQuery();
        emp = criteriaTuple.from(Funcionario.class);
        criteriaTuple.select(
                builder.tuple(
                        emp.get("id").alias("id"),
                        emp.get("nome").alias("nome")
                )
        );
        TypedQuery<Tuple> tuple = em.createQuery(criteriaTuple);

        for (Tuple t : tuple.getResultList()) {
            Integer id = t.get("id", Integer.class);
            String nomeSimples = t.get("nome", String.class);
            System.out.println("Id: " + id + " - Nome: " + nomeSimples);
        }
//
        System.out.println("--------------Usando o IN, JOIN,DISTINCT e LEFT JOIN -----------");
        root = criteria.from(Funcionario.class);
//        root.join("dependentes");
        //Usando DISTINCT
        criteria.distinct(true);//Caso não utilize, executa o LEFT JOIN
        criteria.select(root.join("dependentes").get("nome"));

//        builder.isMember(
//                builder.parameter(Dependente.class, "dep"), 
//                root.<Dependente>get("null"));
        query = em.createQuery(criteria);
        empregados = query.getResultList();

//        for (Funcionario empregado : empregados) {
//            imprimeFuncionario(empregado);
//        }
//
        System.out.println("#7 - Listando os dependentes");
        CriteriaQuery<String> criterio = builder.createQuery(String.class);
        Root<Funcionario> rootEmp = criterio.from(Funcionario.class);
        Join<Funcionario, Dependente> join = rootEmp.join("dependentes");
        criterio.distinct(true);
        criterio.select(join.get("nome"));
        TypedQuery<String> queries = em.createQuery(criterio);
        queries.getResultList().forEach(System.out::println);

        System.out.println("#8 - Listando o nome dos dependentes e Funcionarios");
        CriteriaQuery<Tuple> criterio2 = builder.createTupleQuery();
        Root<Funcionario> rootEmp2 = criterio2.from(Funcionario.class);

        Join<Funcionario, Dependente> join2 = rootEmp2.join("dependentes", JoinType.LEFT);
        //criterio2.distinct(true);
        criterio2.select(
                builder.tuple(
                        join2.get("nome").alias("nomeDep"),
                        rootEmp2.get("nome").alias("nomeEmp"))
        );

        TypedQuery<Tuple> query2 = em.createQuery(criterio2);

        for (Tuple tupla : query2.getResultList()) {
            String nomeDep = tupla.get("nomeDep", String.class);
            String nomeEmp = tupla.get("nomeEmp", String.class);

            System.out.println("Emp: " + nomeEmp + " Dep: " + nomeDep);
        }

//        criteria.add(cb.like(emp.<String>get("nome"), p));
//        criteria.add(cb.like(emp.get(Funcionario_.nome), p));
        System.out.println("--------------Usando BETWEEN-----------");
        System.out.println("#10 - Listando o Funcionarios com id entre 1 e 10");
        root = criteria.from(Funcionario.class);
        Metamodel m = em.getMetamodel();
        EntityType<Funcionario> empMeta = m.entity(Funcionario.class);
        Integer inicio = 1;
        Integer fim = 10;
        criteria.where(
                builder.between(
                        root.get(
                                empMeta.getSingularAttribute("id", Integer.class)
                        ),
                        inicio, fim
                )
        );
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("--------------Usando IS NULL -----------");
        System.out.println("#11 -  Listar todos os Funcionarios que possuem departamento.");
        root = criteria.from(Funcionario.class);
        criteria.where(
                builder.isNotNull(
                        root.get(
                                Funcionario_.departamento
//                                empMeta.getSingularAttribute("departamento")
                        )
                )
        );
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#12 -  Listar todos os Funcionarios que possuem dependentes");
        root = criteria.from(Funcionario.class);
        criteria.where(builder.isNotEmpty(root.get("dependentes")));
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#13 -  Listar o nome dos Dependentes e dos Funcionarios");
        CriteriaQuery<Tuple> criterio13 = builder.createTupleQuery();
        Root<Funcionario> root13 = criterio13.from(Funcionario.class);

        Join<Funcionario, Dependente> join13 = root13.join("dependentes");

        criterio13.select(
                builder.tuple(
                        join13.get("nome").alias("nomeDep"),
                        join13.getParent().get("nome").alias("nomeEmp"))
        );

        TypedQuery<Tuple> query13 = em.createQuery(criterio13);

        query13.getResultList().stream().forEach((tupla) -> {
            String nomeDep = tupla.get("nomeDep", String.class);
            String nomeEmp = tupla.get("nomeEmp", String.class);

            System.out.println("Nome do empregado: " + nomeEmp
                    + " Nome do Dependente: " + nomeDep);
        });

        System.out.println("#14 -  Listar todos os Funcionarios que seus Dependentes "
                + "possuam a letra i como terceira letra do nome");

        Root<Funcionario> root14 = criteria.from(Funcionario.class);
        Join<Funcionario, Dependente> join14 = root14.join("dependentes");
        Predicate like = builder.like(join14.<String>get("nome"), "__i%");
        criteria.select(root14).where(like);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#15 -  Listar todos os Funcionarios que seus "
                + "Dependentes possuam a letra a como segunda letra do nome."
                + " Considere que o nome do Dependente pode estar todo em maíusculo");

        Root<Funcionario> root15 = criteria.from(Funcionario.class);
        Join<Funcionario, Dependente> join15 = root15.join("dependentes");
        Predicate like15 = builder.like(
                builder.upper(join15.<String>get("nome")), "_A%");
//                builder.lower(join15.<String>get("nome")), "_a%");
        criteria.select(root15).where(like15);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#16 -  Listar o nome, em maíusculo, de todos os "
                + "Funcionarios que possuem Dependentes");

        CriteriaQuery<String> criteria16 = builder.createQuery(String.class);
        Root<Funcionario> root16 = criteria16.from(Funcionario.class);
        root16.join("dependentes");
        criteria16.distinct(true);
        criteria16.select(builder.upper(root16.<String>get("nome")));
        TypedQuery<String> query16 = em.createQuery(criteria16);
        query16.getResultList().forEach(System.out::println);

        System.out.println("#17 -  Listar o número de Funcionarios que começam com a letra F");

        //"SELECT COUNT(p) FROM Funcionario p WHERE p.nome LIKE 'R%'
        CriteriaQuery<Long> criteria17 = builder.createQuery(Long.class);
        Root<Funcionario> root17 = criteria17.from(Funcionario.class);
        Predicate like17 = builder.like(
                root17.<String>get("nome"), "F%");
        criteria17.select(builder.count(root17)).where(like17);

        TypedQuery<Long> query17 = em.createQuery(criteria17);
        query17.getResultList().forEach(System.out::println);

        System.out.println("#18 -  Listar todos os Funcionarios ordenados pelo nome");
        Root<Funcionario> root18 = criteria.from(Funcionario.class);
        criteria.orderBy(builder.asc(root18.<String>get("nome")));
        criteria.select(root18);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#19 -  Listar o nome e a quantidade de Dependentes de "
                + "todos os Funcionarios, mesmo os que não possuem Dependentes");

//         consulta = "SELECT p.nome, COUNT(d) FROM Funcionario p"
//                + " LEFT JOIN p.dependentes d GROUP BY p.nome";
        CriteriaQuery<Tuple> criterio19 = builder.createTupleQuery();
        Root<Funcionario> root19 = criterio19.from(Funcionario.class);

        Join<Funcionario, Dependente> join19 = root19.join("dependentes",
                JoinType.LEFT);

        criterio19.select(
                builder.tuple(
                        join19.getParent().get("nome").alias("nome"),
                        builder.count(join19).alias("count")));
        criterio19.groupBy(root19.get("nome"));
        TypedQuery<Tuple> query19 = em.createQuery(criterio19);

        for (Tuple tupla : query19.getResultList()) {
            String nome = tupla.get("nome", String.class);
            Long quantidade = tupla.get("count", Long.class);

            System.out.println("Funcionario: " + nome + " dependentes: " + quantidade);
        }

        System.out.println("#20 -  Listar todos os Funcionarios que possuem"
                + " id maior que a média dos ids de todos os Funcionarios");
//        consulta = "Select p FROM Funcionario p WHERE p.id < (SELECT MAX(p.id) FROM Funcionario p)";
        criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root20 = criteria.from(Funcionario.class);

        Subquery<Double> sq = criteria.subquery(Double.class);
        Root<Funcionario> root_sub20 = sq.from(Funcionario.class);
        sq.select(builder.avg(root_sub20.<Long>get("id")));

        Predicate predicate20 = builder.gt(root20.get("id"), sq);
        criteria.select(root20).where(predicate20);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

        System.out.println("#21 -  Listar o nome dos Funcionarios que seus "
                + "Dependentes possuem id maior que 2");

//        SELECT e FROM Funcionario e WHERE EXISTS 
//        (SELECT d FROM e.dependentes d WHERE d.id > 2)";
        criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root21 = criteria.from(Funcionario.class);

        Subquery<Dependente> sq_21 = criteria.subquery(Dependente.class);
//        Root<Dependente> root_sub21 = sq_21.from(Dependente.class);
        Root<Funcionario> root_sub21 = sq_21.from(Funcionario.class);

        Join< Funcionario, Dependente> join_21 = root_sub21.join("dependentes");
        Predicate predicate21 = builder.gt(join_21.get("codigo"), 2);

        Subquery<Dependente> sqa = sq_21.select(join_21).where(predicate21);

        criteria.select(root21).where(builder.exists(sqa));//.where(predicate21);

        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Funcionario empregado : empregados) {
            imprimeFuncionario(empregado);
        }

    }

    public static void imprimeFuncionario(Funcionario empregado) {
        System.out.println(empregado.getNome());

    }

    public static void imprimeFuncionarioVO(FuncionarioVO empregado) {
        System.out.println(empregado.getId() + " - " + empregado.getNome());

    }
}
