package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.StatusPartida;

import java.util.ArrayList;
import java.util.List;

/**
 * representa uma partida individual do jogo
 *
 * @author ângelo barbosa
 */
public class Partida {

    Jogador jogador, bot;
    Integer rodadaAtual;
    StatusPartida status;


    // métodos funcionais

    /**
     * verifica se a partida está encerrada
     * @return um valor booleando indicado se a partida acabou
     */
    public Boolean acabou() {

        int jogadoresDerrotados = 0;

        List<Jogador> jogadores = new ArrayList<>(List.of(jogador, bot));

        for (Jogador jogador : jogadores) {
            if (jogador.isDerrotado())
                ++jogadoresDerrotados;
        }

        return jogadoresDerrotados == jogadores.size() - 1;
    }

    /**
     * executa o movimento do jogador humano
     * @param tiro tiro escolhido pelo jogador
     * @return resultado do tiro executado
     */
    public ResultadoTurno turnoJogador(Tiro tiro) {
        return jogador.jogaTurno(tiro, bot);
    }

    /**
     * executa o movimento do bot
     * @return resultado do tiro executado
     */
    public ResultadoTurno turnoBot() {

        Casa casaEscolhida = jogador.getTabuleiro().getCasaSemNavioAleatoria();
        Tiro tiro = new Tiro(casaEscolhida.getI(), casaEscolhida.getJ());

        ResultadoTurno resultado = bot.jogaTurno(tiro, jogador);

        return resultado;
    }

    // método construtor

    /**
     * constrói a partida dado seus jogadores
     * @param jogador jogador humano
     * @param bot jogador bot
     */
    public Partida(Jogador jogador, Jogador bot) {
        rodadaAtual = 1;
        this.jogador = jogador;
        this.bot = bot;
        this.status = StatusPartida.AGUARDANDO_SELECAO_CASA;
    }

    // métodos de acesso

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getBot() {
        return bot;
    }

    public void setBot(Jogador bot) {
        this.bot = bot;
    }

    public Integer getRodadaAtual() {
        return rodadaAtual;
    }

    public void incrementarRodada() {
        this.rodadaAtual++;
    }

    public StatusPartida getStatus() {
        return status;
    }

    public void setStatus(StatusPartida status) {
        this.status = status;
    }
}
