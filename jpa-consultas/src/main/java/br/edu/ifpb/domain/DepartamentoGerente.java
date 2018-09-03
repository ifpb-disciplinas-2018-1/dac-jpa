package br.edu.ifpb.domain;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 30/08/2018, 09:26:44
 */
public class DepartamentoGerente {

    private String abreviacao;
    private Gerente gerente;

    public DepartamentoGerente(String abreviacao, Gerente gerente) {
        this.abreviacao = abreviacao;
        this.gerente = gerente;
    }

    public DepartamentoGerente() {
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    @Override
    public String toString() {
        return "DepartamentoGerente{" + "abreviacao=" + abreviacao + ", gerente=" + gerente + '}';
    }

}
