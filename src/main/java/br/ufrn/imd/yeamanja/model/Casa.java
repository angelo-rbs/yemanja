package br.ufrn.imd.yeamanja.model;

import static br.ufrn.imd.yeamanja.model.Constante.DIMENSAO_TABULEIRO;

/**
 * representa uma casa do {@link Tabuleiro}
 *
 * @author  ângelo barbosa
 */
public class Casa {

    private Embarcacao temEmbarcacao = null;
    private Tiro temTiro = null;
    private final Integer i, j;

    // métodos funcionais

    public Boolean isBorda() {
        return isDentroDoTabuleiro()
                && (i == 0 || j == 0 || i == DIMENSAO_TABULEIRO - 1 || j == DIMENSAO_TABULEIRO - 1);
    }

    public Boolean isCanto() {
        return isDentroDoTabuleiro()
        && (i == 0 && j == 0)
                || (i == 0 && j == DIMENSAO_TABULEIRO - 1)
                || (i == DIMENSAO_TABULEIRO - 1 && j == 0)
                || (i == DIMENSAO_TABULEIRO - 1 && j == DIMENSAO_TABULEIRO - 1);
    }

    public Boolean isBordaLateral() {
        return this.isBorda() && !this.isCanto();
    }

    public Boolean isDentroDoTabuleiro() {
        return i >= 0 && i < DIMENSAO_TABULEIRO && j >= 0 && j < DIMENSAO_TABULEIRO;
    }

    // construtor

    public Casa(Integer i, Integer j) {
        temEmbarcacao = null;
        temTiro = null;
        this.i = i;
        this.j = j;
    }

    //  métodos de acesso

    public Integer getI() {
        return i;
    }

    public Integer getJ() {
        return j;
    }

    public Embarcacao getTemNavio() {
        return temEmbarcacao;
    }

    public void setTemNavio(Embarcacao temEmbarcacao) {
        this.temEmbarcacao = temEmbarcacao;
    }

    public Tiro getTemTiro() {
        return temTiro;
    }

    public void setTemTiro(Tiro temTiro) {
        this.temTiro = temTiro;
    }
}
