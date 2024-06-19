package br.ufrn.imd.yeamanja.controller;

import br.ufrn.imd.yeamanja.model.*;
import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.StatusPartida;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class JogoController implements Initializable {

    @FXML
    GridPane oceanoAmigo;

    @FXML
    GridPane oceanoInimigo;

    @FXML
    Button botaoAcao;

    @FXML
    Label statusJogo;

    @FXML
    Label infoRodada;

    Pane casaSelecionada;

    Partida partida;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // criação da partida
        Jogador pessoa = new JogadorHumano("ângelo");
        Jogador bot = new JogadorBot("bot");
        partida = new Partida(pessoa, bot);

        // pintando os submarinos do player
        List<Embarcacao> embarcacoesPlayer = partida.getJogador().getTabuleiro().getEmbarcacoes();
        List<Casa> casasOcupadasPlayer = partida.getJogador().getTabuleiro().getCasasOcupadasPorEmbarcacao(embarcacoesPlayer);
        Pane pane;
        for (Casa casa : casasOcupadasPlayer) {
            pane = getNodeByRowColumnIndex(casa.getI(), casa.getJ(), oceanoAmigo);
            pane.setStyle(EstiloCasa.OCUPADA_POR_NAVIO);

            Label identificador = new Label();
            identificador.setText(casa.getTemNavio().getCumprimentoEmCasas().toString());
            pane.getChildren().add(identificador);
        }

        System.out.println("Jogo inicializado");
    }

    @FXML
    private void celulaAmigaClicada(MouseEvent event) {

        if (!getStatusPartida().equals(StatusPartida.AGUARDANDO_SELECAO_CASA )) return;

        Pane casaClicada = (Pane) event.getSource();
        Tabuleiro tabuleiroJogador = partida.getJogador().getTabuleiro();

        // remove estilo de seleção da célula selecionada anterior
        if (casaSelecionada != null) {

            Boolean casaSelecionadaEhAliada = ehCelulaAliada(casaSelecionada);
            Tabuleiro tabuleiroCasaSelecionada = casaSelecionadaEhAliada
                    ? partida.getJogador().getTabuleiro()
                    : partida.getBot().getTabuleiro();

            if (casaAtingida(casaSelecionada, tabuleiroCasaSelecionada)) {
                if (casaOcupada(casaSelecionada, tabuleiroCasaSelecionada))
                    casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA);
                else
                    casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO);

            } else {
                if (casaOcupada(casaSelecionada, tabuleiroCasaSelecionada))
                    casaSelecionada.setStyle(casaSelecionadaEhAliada ? EstiloCasa.OCUPADA_POR_NAVIO : EstiloCasa.NORMAL);
                else
                    casaSelecionada.setStyle(EstiloCasa.NORMAL);
            }
        }

        casaSelecionada = casaClicada;

        // estiliza nova céula selecionada
        if (casaAtingida(casaSelecionada, tabuleiroJogador)) {
            if (casaOcupada(casaSelecionada, tabuleiroJogador))
                casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA_AMIGA_SELECIONADA);
            else
                casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO_AMIGA_SELECIONADA);

        } else {
            if (casaOcupada(casaSelecionada, tabuleiroJogador))
                casaSelecionada.setStyle(EstiloCasa.OCUPADA_POR_NAVIO_SELECIONADA);
            else
                casaSelecionada.setStyle(EstiloCasa.CASA_AMIGA_SELECIONADA);
        }

        botaoAcao.setDisable(true);
    }

    @FXML
    private void celulaInimigaClicada(MouseEvent event) {

        if (!getStatusPartida().equals(StatusPartida.AGUARDANDO_SELECAO_CASA)) return;

        Pane casaClicada = (Pane) event.getSource();
        Tabuleiro tabuleiroBot = partida.getBot().getTabuleiro();

        // remove estilo de seleção da célula selecionada anterior
        if (casaSelecionada != null) {

            Boolean casaSelecionadaEhAliada = ehCelulaAliada(casaSelecionada);
            Tabuleiro tabuleiroCasaSelecionada = casaSelecionadaEhAliada
                    ? partida.getJogador().getTabuleiro()
                    : partida.getBot().getTabuleiro();

            if (casaAtingida(casaSelecionada, tabuleiroCasaSelecionada)) {
                if (casaOcupada(casaSelecionada, tabuleiroCasaSelecionada))
                    casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA);
                else
                    casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO);

            } else {
                if (casaOcupada(casaSelecionada, tabuleiroCasaSelecionada))
                    casaSelecionada.setStyle(casaSelecionadaEhAliada ? EstiloCasa.OCUPADA_POR_NAVIO : EstiloCasa.NORMAL);
                else
                    casaSelecionada.setStyle(EstiloCasa.NORMAL);
            }
        }

        casaSelecionada = casaClicada;

        // estiliza nova célula selecionada
        if (casaAtingida(casaSelecionada, tabuleiroBot)) {
            if (casaOcupada(casaSelecionada, tabuleiroBot))
                casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA_INIMIGA_SELECIONADA);
            else
                casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO_INIMIGA_SELECIONADA);

        } else
            casaSelecionada.setStyle(EstiloCasa.CASA_INIMIGA_SELECIONADA);

        botaoAcao.setDisable(false);
    }

    public void handleIniciarRodada() throws InterruptedException {


        if (getStatusPartida().equals(StatusPartida.AGUARDANDO_PROMPT_REINICIAR)) {

            reiniciarPartida();

            setStatusPartida(StatusPartida.AGUARDANDO_SELECAO_CASA);
            botaoAcao.setText("Atirar!");
            botaoAcao.setDisable(true);
            statusJogo.setText("Selecione uma casa para atirar!");
            infoRodada.setText(String.format("Rodada %d", partida.getRodadaAtual()));
            oceanoAmigo.setDisable(false);
            oceanoInimigo.setDisable(false);

            return;
        }

        // Jogador atira
        botaoAcao.setDisable(true);
        String id = casaSelecionada.getId();
        Integer i = Integer.parseInt(id.substring(id.length() - 1));
        Integer j = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));
        Tiro tiro = new Tiro(i, j);


        PauseTransition animacao = new PauseTransition(new Duration(800));
        animacao.setOnFinished(evt -> {
            try {
                playAnimacaoTiroPlayer(tiro);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }});


        SequentialTransition sequentialTransition = new SequentialTransition(animacao);
        sequentialTransition.play();


        if (getStatusPartida().equals(StatusPartida.AGUARDANDO_PROMPT_REINICIAR)) return;
        informar("Embarcação inimiga atingida!");

        // Bot atira
        PauseTransition animacaoBot = new PauseTransition(new Duration(800));
        animacaoBot.setOnFinished(evt -> {
            try {
                playAnimacaoTiroBot();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        SequentialTransition transicaoBot = new SequentialTransition(animacaoBot);
        transicaoBot.play();

        if (getStatusPartida().equals(StatusPartida.AGUARDANDO_PROMPT_REINICIAR)) return;


        partida.incrementarRodada();
        infoRodada.setText(String.format("Rodada %d", partida.getRodadaAtual()));
    }

    public void informar(String mensagem) throws InterruptedException {

        String mensagemAnterior = statusJogo.getText();

        PauseTransition inicio = new PauseTransition(new Duration(0));
        inicio.setOnFinished(evt -> {
            statusJogo.setText(mensagem);
            botaoAcao.setDisable(true);
        });

        PauseTransition voltarEstadoAnterior = new PauseTransition(new Duration(500));
        voltarEstadoAnterior.setOnFinished(evt -> {
            statusJogo.setText(mensagemAnterior);
            botaoAcao.setDisable(false);
        });

        SequentialTransition sequentialTransition = new SequentialTransition(inicio, voltarEstadoAnterior);
        sequentialTransition.play();
    }

    public void playAnimacaoTiroPlayer(Tiro tiro) throws InterruptedException {

        PauseTransition inicio = new PauseTransition(new Duration(0));
        inicio.setOnFinished(evt -> {
            statusJogo.setText("Preparar...");
        });

        PauseTransition preparar = new PauseTransition(new Duration(500));
        preparar.setOnFinished(evt -> {
            statusJogo.setText("Apontar...");
        });

        PauseTransition apontar = new PauseTransition(new Duration(500));
        apontar.setOnFinished(evt -> {
            statusJogo.setText("Fogo!");
        });

        PauseTransition fogo = new PauseTransition(new Duration(1000));
        fogo.setOnFinished(e -> {

            ResultadoTurno resultado = partida.turnoJogador(tiro);
            Pane paneAtingido = getNodeByRowColumnIndex(
                    resultado.getCasaAtacada().getI(),
                    resultado.getCasaAtacada().getJ(),
                    oceanoInimigo);

            paneAtingido.setStyle(resultado.getEmbarcacaoAtingida() ? EstiloCasa.EMBARCACAO_ATINGIDA : EstiloCasa.ATINGIDA_SEM_SUCESSO);

            System.out.format("Rodada %d: Fim do turno do jogador %s\n", partida.getRodadaAtual(), partida.getJogador().getNome());
        });

        PauseTransition encerramento = new PauseTransition(new Duration(800));
        encerramento.setOnFinished(e -> {
            if (partida.acabou()) {
                partida.setStatus(StatusPartida.AGUARDANDO_PROMPT_REINICIAR);

                oceanoAmigo.setDisable(true);
                oceanoInimigo.setDisable(true);
                statusJogo.setText("Boa batalha, capitão. Partir iniciar a próxima batalha?");
                botaoAcao.setDisable(false);
                botaoAcao.setText("Reiniciar");
            } else
                statusJogo.setText("Selecione uma casa para atirar");
        });

        SequentialTransition sequentialTransition = new SequentialTransition(inicio, preparar, apontar, fogo, encerramento);
        sequentialTransition.play();
    }

    public Boolean playAnimacaoTiroBot() throws InterruptedException {

        PauseTransition inicio = new PauseTransition(new Duration(0));
        inicio.setOnFinished(evt -> {
            statusJogo.setText("Preparar...");
        });

        PauseTransition preparar = new PauseTransition(new Duration(500));
        preparar.setOnFinished(evt -> {
            statusJogo.setText("Apontar...");
        });

        PauseTransition apontar = new PauseTransition(new Duration(500));
        apontar.setOnFinished(evt -> {
            statusJogo.setText("Fogo!");
        });

        PauseTransition fogo = new PauseTransition(new Duration(1000));
        fogo.setOnFinished(e -> {
            ResultadoTurno resultadoTurno = partida.turnoBot();
            Pane paneAtingido = getNodeByRowColumnIndex(
                    resultadoTurno.getCasaAtacada().getI(),
                    resultadoTurno.getCasaAtacada().getJ(),
                    oceanoAmigo);

            paneAtingido.setStyle(resultadoTurno.getEmbarcacaoAtingida() ? EstiloCasa.EMBARCACAO_ATINGIDA : EstiloCasa.ATINGIDA_SEM_SUCESSO);


            System.out.format("Rodada %d: Fim do turno do bot %s\n", partida.getRodadaAtual(), partida.getJogador().getNome());
        });

        PauseTransition encerramento = new PauseTransition(new Duration(0));
        encerramento.setOnFinished(e -> {
            if (partida.acabou()) {

                partida.setStatus(StatusPartida.AGUARDANDO_PROMPT_REINICIAR);
                System.out.println("Partida acabou: bot vence");
                statusJogo.setText("Você perdeu.");
                botaoAcao.setText("Reiniciar");


            } else {
                statusJogo.setText("Selecione uma casa para atirar");
            }
        });

        SequentialTransition sequentialTransition = new SequentialTransition(inicio, preparar, apontar, fogo, encerramento);
        sequentialTransition.play();

        return partida.acabou();
    }

    public static <T> T getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (T) node;
            }
        }
        return null;
    }

    public Boolean casaAtingida(Pane casa, Tabuleiro tabuleiro) {

        String id = casa.getId();
        Integer i = Integer.parseInt(id.substring(id.length() - 1));
        Integer j = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));

        return tabuleiro.getCasa(i, j).getTemTiro() != null;
    }

    public Boolean casaOcupada(Pane casa, Tabuleiro tabuleiro) {
        String id = casa.getId();
        Integer i = Integer.parseInt(id.substring(id.length() - 1));
        Integer j = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));

        return tabuleiro.getCasa(i, j).getTemNavio() != null;
    }

    private StatusPartida getStatusPartida() {
        return partida.getStatus();
    }

    private void setStatusPartida(StatusPartida statusPartida) {
        partida.setStatus(statusPartida);
    }

    private Boolean ehCelulaInimiga(Pane pane) {
        return pane.getId().startsWith("enemyCell");
    }

    private Boolean ehCelulaAliada(Pane pane) {
        return pane.getId().startsWith("friendCell");
    }

    void reiniciarPartida() {

        casaSelecionada = null;

        // criação da partida
        Jogador pessoa = new JogadorHumano("ângelo");
        Jogador bot = new JogadorBot("bot");
        partida = new Partida(pessoa, bot);

        // reset tabuleiro amigo
        Pane pane;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pane = getNodeByRowColumnIndex(i, j, oceanoAmigo);
                pane.setStyle(EstiloCasa.NORMAL);
                pane.getChildren().forEach(node -> {
                    Label label = (Label) node;
                    label.setText("");
                });
            }
        }

        // reset tabuleiro inimigo
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pane = getNodeByRowColumnIndex(i, j, oceanoInimigo);
                pane.setStyle(EstiloCasa.NORMAL);
                pane.getChildren().forEach(node -> {
                    Label label = (Label) node;
                    label.setText("");
                });
            }
        }

        // pintando os submarinos do player
        List<Embarcacao> embarcacoesPlayer = partida.getJogador().getTabuleiro().getEmbarcacoes();
        List<Casa> casasOcupadasPlayer = partida.getJogador().getTabuleiro().getCasasOcupadasPorEmbarcacao(embarcacoesPlayer);
        for (Casa casa : casasOcupadasPlayer) {
            pane = getNodeByRowColumnIndex(casa.getI(), casa.getJ(), oceanoAmigo);
            pane.setStyle(EstiloCasa.OCUPADA_POR_NAVIO);

            Label identificador = new Label();
            identificador.setText(casa.getTemNavio().getCumprimentoEmCasas().toString());
            pane.getChildren().add(identificador);
        }
    }
}
