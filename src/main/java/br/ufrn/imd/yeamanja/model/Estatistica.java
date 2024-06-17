package br.ufrn.imd.yeamanja.model;

public class Estatistica {

    public int vitorias;
    public int derrotas;


    // método construtor

    public Estatistica() {
        vitorias = 0;
        derrotas = 0;
    }

    // métodos de acesso

    public int getVitorias() {
        return vitorias;
    }


    public int getDerrotas() {
        return derrotas;
    }

    public void registraVitoria() {
        this.vitorias++;
    }

    public void registraDerrotas() {
        this.derrotas++;
    }
}
