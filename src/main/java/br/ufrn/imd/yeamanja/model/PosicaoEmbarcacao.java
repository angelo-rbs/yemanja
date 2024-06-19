package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;

/**
 * repreesnta a posição de uma embarcação no {@link Tabuleiro}
 *
 */
public class PosicaoEmbarcacao {

    private Casa casaPivot;
    private Orientacao orientacao;


    // método construtor
    public PosicaoEmbarcacao(Casa casaPivot, Orientacao orientacao) {
        this.casaPivot = casaPivot;
        this.orientacao = orientacao;
    }


    // métodos de aecsso
    public Casa getCasaPivot() {
        return casaPivot;
    }

    public void setCasaPivot(Casa casaPivot) {
        this.casaPivot = casaPivot;
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }
}
