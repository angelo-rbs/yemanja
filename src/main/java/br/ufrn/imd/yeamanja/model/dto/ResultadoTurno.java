package br.ufrn.imd.yeamanja.model.dto;

import br.ufrn.imd.yeamanja.model.Casa;

public class ResultadoTurno {

    private Boolean embarcacaoAtingida;
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
