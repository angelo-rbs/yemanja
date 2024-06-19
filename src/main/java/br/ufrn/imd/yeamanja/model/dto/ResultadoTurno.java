package br.ufrn.imd.yeamanja.model.dto;

import br.ufrn.imd.yeamanja.model.Casa;

/**
 * representa os resultados de um turno jogado pelo {@link br.ufrn.imd.yeamanja.model.Jogador}
 *
 * @author  ângelo barbosa
 */
public class ResultadoTurno {

    /**
     * indica se o tiro atingiu uma embarcação
     */
    private Boolean embarcacaoAtingida;

    /**
     * indica a casa do {@link br.ufrn.imd.yeamanja.model.Tabuleiro} atacada
     */
    private Casa casaAtacada;

    public ResultadoTurno(Boolean embarcacaoAtingida, Casa casaAtacada) {
        this.embarcacaoAtingida = embarcacaoAtingida;
        this.casaAtacada = casaAtacada;
    }

    public Boolean getEmbarcacaoAtingida() {
        return embarcacaoAtingida;
    }

    public Casa getCasaAtacada() {
        return casaAtacada;
    }
}
