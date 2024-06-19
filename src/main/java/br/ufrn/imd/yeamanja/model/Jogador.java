package br.ufrn.imd.yeamanja.model;


import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;
import br.ufrn.imd.yeamanja.model.interfaces.IJogador;

/**
 * representa um jogador abstrato
 *
 * @author  ângelo barbosa
 *
 */
public abstract class Jogador implements IJogador {

    private String nome;
    private Tabuleiro tabuleiro;
    private Estatistica estatistica;
    private TipoJogador tipo;

    /**
     * método abstrato da ação de jogar um turno
     * @param tiro tiro escolhido pelo jogador
     * @param adversario adversário alvo
     * @return
     */
    @Override
    public abstract ResultadoTurno jogaTurno(Tiro tiro, Jogador adversario);

    /**
     * verifica se o jogador teve todos os seus navios afundados
     * @return valor booleano indicando se o jogador foi derrotado
     */
    public boolean isDerrotado() {
        return tabuleiro.getEmbarcacoes().stream().allMatch(embarcacao -> embarcacao.isNavioAfundado());
    }

    // método construtor

    public Jogador(String nome) {
        this.nome = nome;
        this.tabuleiro = new Tabuleiro();
        this.estatistica = new Estatistica();
    }

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

    public Estatistica getEstatistica() {
        return estatistica;
    }

    public void setEstatistica(Estatistica estatistica) {
        this.estatistica = estatistica;
    }

    public TipoJogador getTipo() {
        return tipo;
    }

    public void setTipo(TipoJogador tipo) {
        this.tipo = tipo;
    }
}
