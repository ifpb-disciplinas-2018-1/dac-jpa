package br.edu.ifpb;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 16/08/2018, 08:10:52
 */
@Named
@SessionScoped
public class ControladorDePessoa implements Serializable {

    private Pessoa pessoa = new Pessoa();
    @Inject
    private ServicePessoa service;

    public String salvar() {
        // Context 1
        this.service.salvar(pessoa);
        // Context 2
        this.service.atualizarPessoa(pessoa);
//        this.pessoa.setNome("Maria");
        this.pessoa = new Pessoa();
        return null;
    }

    public String atualizar() {
        this.service.atualizar(pessoa);
        this.pessoa = new Pessoa();
        return null;
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        return null;
    }

    public String remover(Pessoa pessoa) {
        this.service.remover(pessoa);
        return null;
    }

    public List<Pessoa> listar() {
        return this.service.listarTodas();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
