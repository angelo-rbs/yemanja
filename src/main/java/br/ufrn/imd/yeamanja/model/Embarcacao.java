package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;

public class Navio {

    Casa casaPivot;
    Orientacao orientacao;
    Integer cumprimentoEmCasas;

    // métodos funcionais



    // construtor

    public Navio(Casa casaPivot, Orientacao orientacao) {
        this.casaPivot = casaPivot;
        this.orientacao = orientacao;
    }


    // métodos de acesso

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

    public Integer getCumprimentoEmCasas() {
        return cumprimentoEmCasas;
    }

    public void setCumprimentoEmCasas(Integer cumprimentoEmCasas) {
        this.cumprimentoEmCasas = cumprimentoEmCasas;
    }
}
