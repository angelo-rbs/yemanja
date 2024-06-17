package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.StatusPartida;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    Jogador jogador, bot;
    Integer rodadaAtual;
    StatusPartida status;



    // métodos funcionais

    public Boolean partidaAcabou() {

        int jogadoresDerrotados = 0;

        List<Jogador> jogadores = new ArrayList<>(List.of(jogador, bot));

        for (Jogador jogador : jogadores) {
            if (jogador.isDerrotado())
                ++jogadoresDerrotados;
        }

        return jogadoresDerrotados == jogadores.size() - 1;
    }

    public Jogador getJogadorVencedor() {

        if (!partidaAcabou()) return null;
        List<Jogador> jogadores = new ArrayList<>(List.of(jogador, bot));


        for (Jogador jogador : jogadores)
            if (!jogador.isDerrotado()) return jogador;

        return null;
    }

    public Boolean turnoJogador(Tiro tiro) {

        Boolean acertouNavio = jogador.jogaTurno(tiro, bot);
        System.out.format("Rodada %d: Fim do turno do jogador %s\n", rodadaAtual, jogador.getNome());

        return acertouNavio;
    }

    public Boolean turnoBot() {

        Casa casaAtingida = jogador.getTabuleiro().getCasaSemNavioAleatoria();
        Tiro tiro = new Tiro(casaAtingida.getI(), casaAtingida.getJ());
        Boolean navioAtingido = bot.jogaTurno(tiro, jogador);
        System.out.format("Rodada %d: Fim do turno do computador %s\n", rodadaAtual, bot.getNome());

        return navioAtingido;
    }

    // método construtor

    public Partida(Jogador jogador, Jogador bot) {
        rodadaAtual = 1;
        this.jogador = jogador;
        this.bot = bot;
        this.status = StatusPartida.AGUARDANDO_SELECAO_CASA;
    }

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
