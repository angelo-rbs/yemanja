package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;

public class NavioCoverta extends Navio {

    public NavioCoverta(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.cumprimentoEmCasas = Constants.DIMENSAO_CORVETA;
    }
}
