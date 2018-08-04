package br.edu.ifpb.main;

import br.edu.ifpb.domain.Aluno;
import br.edu.ifpb.domain.Contato;
import br.edu.ifpb.domain.ImagePath;
import br.edu.ifpb.domain.Perfil;
import br.edu.ifpb.domain.Professor;
import br.edu.ifpb.domain.Sexo;
import br.edu.ifpb.domain.Tecnico;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 30/07/2018, 07:25:48
 */
public class Main {

    public static void main(String[] args) {
//        salvarContato();
//        salvarProfessor();
//        salvarAluno();
//        salvarPerfil();
//        salvarTecnico();
        

    }

//    private static void salvar(Contato contato) {
//        try {
//            Connection c = null;
//            String sql = "INSERT INTO Contato VALUES (?,?,?);";
//            PreparedStatement createStatement = c.prepareStatement(sql);
//            
//            //mapeamento
//            createStatement.setString(1, "123456");
//            createStatement.setString(2, "Kiko");
//            createStatement.setString(3, "kiko@chave.org");
//            
//            
//            createStatement.execute(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    private static void salvarContato() {
        Contato contato = Contato.of("1234", "kiko", "kiko@chaves.com");

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU2")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(contato);
//        Contato contatoDoBanco = manager.find(Contato.class, "123");
//        System.out.println(contatoDoBanco.getNome());
//        contatoDoBanco.setNome("Madruga");
//        manager.merge(contatoDoBanco);
        transaction.commit();
    }

    private static void salvarProfessor() {
        Professor professor = new Professor(
                1000.0, "120", Sexo.FEMININO
        );

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(professor); //dentro de uma transação
        transaction.commit();
    }

    private static void salvarAluno() {
        Aluno aluno = new Aluno("Florinda", 9.4);

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(aluno); //dentro de uma transação
        transaction.commit();
    }

    private static void salvarPerfil() {
        byte[] foto = new ImagePath("/Users/job/Documents/dev/testes/20181/dac-jpa/src/main/resources/imagem/chaves.jpeg")
                .toBytes();
        Perfil perfil = new Perfil("kiko", foto);
        perfil.setDescricao("Chespirito, onde uma criança de oito anos discutia com "
                + "um vendedor de balões em um parque (interpretado por Ramón Valdez)."
                + "[6] Bolaños deu importância ao desenvolvimento dos personagens, aos"
                + " quais foram distribuídas personalidades distintas. Desde o início, "
                + "seu criador percebeu que o seriado seria destinado ao público de todas "
                + "as idades, mesmo se tratando de adultos interpretando crianças.[3] "
                + "O elenco principal é composto por Roberto Bolaños, María Antonieta "
                + "de las Nieves, Ramón Valdés, Florinda Meza, Carlos Villagrán, Edgar "
                + "Vivar, Rubén Aguirre, Angelines Fernández, Horacio Gómez e Raúl Padilla.[7]");
        
        
        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU2")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(perfil); //dentro de uma transação
        transaction.commit();
    }

    private static void salvarTecnico() {
        Tecnico tecnico = new Tecnico(
                "Low", "124", "123456"
        );

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(tecnico); //dentro de uma transação
        transaction.commit();
    }
}
