package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.StatusPartida;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class Partida {

    Jogador jogador, bot;
    Integer rodadaAtual;
    StatusPartida status;


    // métodos funcionais

    public Boolean acabou() {

        System.out.println("Tabuleiro do jogador antes do teste:");
        jogador.getTabuleiro().print();
        System.out.println();

        System.out.println("Tabuleiro do bot antes do teste:");
        bot.getTabuleiro().print();
        System.out.println();

        int jogadoresDerrotados = 0;

        List<Jogador> jogadores = new ArrayList<>(List.of(jogador, bot));

        for (Jogador jogador : jogadores) {
            if (jogador.isDerrotado())
                ++jogadoresDerrotados;
        }

        return jogadoresDerrotados == jogadores.size() - 1;
    }


    public ResultadoTurno turnoJogador(Tiro tiro) {
        return jogador.jogaTurno(tiro, bot);
    }

    public ResultadoTurno turnoBot() {

        Casa casaEscolhida = jogador.getTabuleiro().getCasaSemNavioAleatoria();
        Tiro tiro = new Tiro(casaEscolhida.getI(), casaEscolhida.getJ());

        ResultadoTurno resultado = bot.jogaTurno(tiro, jogador);

        return resultado;
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
