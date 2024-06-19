package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

import java.util.List;

/**
 * representa uma embarcação genérica
 *
 * @author  ângelo barbosa
 */
public class Embarcacao {

    PosicaoEmbarcacao posicao;
    TipoEmbarcacao tipoEmbarcacao;
    List<Casa> casasOcupadas;

    // métodos funcionais

    public Boolean isNavioAfundado() {
        return casasOcupadas.stream().allMatch(casa -> casa.getTemTiro() != null);
    }

    // construtor

    public Embarcacao(Casa casaPivot, Orientacao orientacao) {
        posicao = new PosicaoEmbarcacao(casaPivot, orientacao);
    }

    public Embarcacao() {

    }


    // métodos de acesso


    public PosicaoEmbarcacao getPosicao() {
        return posicao;
    }

    public void setPosicao(PosicaoEmbarcacao posicao) {
        this.posicao = posicao;
    }

    public Casa getCasaPivot() {
        return posicao.getCasaPivot();
    }

    public void setCasaPivot(Casa casaPivot) {
        posicao.setCasaPivot(casaPivot);
    }

    public Orientacao getOrientacao() {
        return posicao.getOrientacao();
    }

    public void setOrientacao(Orientacao orientacao) {
        posicao.setOrientacao(orientacao);
    }


    public TipoEmbarcacao getTipoEmbarcacao() {
        return tipoEmbarcacao;
    }

    public void setTipoEmbarcacao(TipoEmbarcacao tipoEmbarcacao) {
        this.tipoEmbarcacao = tipoEmbarcacao;
    }

    public Integer getCumprimentoEmCasas() {
        return tipoEmbarcacao.getTamanho();
    }

    public List<Casa> getCasasOcupadas() {
        return casasOcupadas;
    }

    public void setCasasOcupadas(List<Casa> casasOcupadas) {
        this.casasOcupadas = casasOcupadas;
    }
}
