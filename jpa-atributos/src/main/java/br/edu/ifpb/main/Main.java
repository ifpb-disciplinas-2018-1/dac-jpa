package br.edu.ifpb.main;

import br.edu.ifpb.domain.Aluno;
import br.edu.ifpb.domain.Contato;
import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.ImagePath;
import br.edu.ifpb.domain.Perfil;
import br.edu.ifpb.domain.Professor;
import br.edu.ifpb.domain.Sexo;
import br.edu.ifpb.domain.Tecnico;
import br.edu.ifpb.domain.chaves.Telefone;
import br.edu.ifpb.domain.chaves.TelefonePK;
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
        salvarTelefone();

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
                1000.0, "120", Sexo.FEMININO, "12345678909"
        );

        professor.adicionarTelefone("(83) 3532-4165");
        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(professor); //dentro de uma transação
        transaction.commit();
    }

    private static void salvarAluno() {

        Endereco endereco = new Endereco(
                "minha rua", "meu bairro", "minha cidade"
        );
        Aluno aluno = new Aluno("Florinda", 9.4);
        aluno.setEndereco(endereco);

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(aluno); //dentro de uma transação
        transaction.commit();
    }

    private static void salvarPerfil() {
        byte[] foto = new ImagePath("src/main/resources/imagem/chaves.jpeg")
                .toBytes();
        Perfil perfil = new Perfil("Chaves", foto);
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
                .createEntityManagerFactory("ExemploPU")
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

    private static void salvarTelefone() {
        Telefone telefone = new Telefone(
                "83", "35324100"
        );

        EntityManager manager = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(telefone); //dentro de uma transação
        transaction.commit();
        //Aluno find = manager.find(Aluno.class, 7);
//        TelefonePK telefonePK = new TelefonePK("83", "35324100");
//        Telefone tel = manager.find(Telefone.class, telefonePK);
//        System.out.println(tel.getChave().getNumero());
    }
}
