package br.ufrn.imd.yeamanja.model;


public abstract class Jogador {

    private String nome;
    private Tabuleiro tabuleiro;


    // métodos de acesso

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
}
