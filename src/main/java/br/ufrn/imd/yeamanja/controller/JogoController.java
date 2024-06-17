package br.ufrn.imd.yeamanja.controller;

import br.ufrn.imd.yeamanja.model.*;
import br.ufrn.imd.yeamanja.model.enumerations.StatusPartida;
import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    TipoJogador jogadorCasaSelecionada;

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
        }

        System.out.println("Jogo inicializado");

    }

    @FXML
    private void celulaAmigaClicada(MouseEvent event) {

        Pane casaClicada = (Pane) event.getSource();
        Tabuleiro tabuleiroJogador = partida.getJogador().getTabuleiro();

        // remove estilo de seleção da célula selecionada anterior
        if (casaSelecionada != null) {
            if (casaAtingida(casaSelecionada, tabuleiroJogador)) {
                if (casaOcupada(casaSelecionada, tabuleiroJogador))
                    casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA);
                else
                    casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO);

            } else {
                if (casaOcupada(casaSelecionada, tabuleiroJogador))
                    casaSelecionada.setStyle(EstiloCasa.OCUPADA_POR_NAVIO);
                else
                    casaSelecionada.setStyle(EstiloCasa.NORMAL);
            }
        }

        casaSelecionada = casaClicada;
        jogadorCasaSelecionada = TipoJogador.HUMANO;

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

        Tabuleiro tabuleiroBot = partida.getBot().getTabuleiro();

        if (getStatusPartida().equals(StatusPartida.AGUARDANDO_SELECAO_CASA)) {

            Pane casaClicada = (Pane) event.getSource();

            // remove estilo de seleção da célula selecionada anterior
            if (casaSelecionada != null) {
                if (casaAtingida(casaSelecionada, tabuleiroBot)) {
                    if (casaOcupada(casaSelecionada, tabuleiroBot))
                        casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA);
                    else
                        casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO);

                } else
                    casaSelecionada.setStyle(EstiloCasa.NORMAL);
            }

            casaSelecionada = casaClicada;
            jogadorCasaSelecionada = TipoJogador.BOT;

            // estiliza nova célula selecionada
            if (casaAtingida(casaSelecionada, tabuleiroBot)) {
                if (casaOcupada(casaSelecionada, tabuleiroBot))
                    casaSelecionada.setStyle(EstiloCasa.EMBARCACAO_ATINGIDA_INIMIGA_SELECIONADA);
                else
                    casaSelecionada.setStyle(EstiloCasa.ATINGIDA_SEM_SUCESSO_INIMIGA_SELECIONADA);

            } else
                casaSelecionada.setStyle(EstiloCasa.CASA_INIMIGA_SELECIONADA);



            if (isEnemyCell(casaClicada)) {
                botaoAcao.setDisable(false);
            } else {
                setStatusPartida(StatusPartida.AGUARDANDO_SELECAO_CASA);
                botaoAcao.setDisable(true);
            }
        }

    }

    public void handleIniciarRodada() throws InterruptedException {


        if (getStatusPartida().equals(StatusPartida.AGUARDANDO_PROMPT_REINICIAR)) {

            // reinicia rodada

            System.out.println("reinicia rodada");

            return;
        }

        // Jogador atira
        String id = casaSelecionada.getId();
        Integer i = Integer.parseInt(id.substring(id.length() - 1));
        Integer j = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));
        Tiro tiro = new Tiro(i, j);
        playAnimacaoTiroPlayer(tiro);

        if (partida.partidaAcabou()) {
            System.out.println("Partida acabou: jogador vence");

            statusJogo.setText("Boa batalha, capitão. Partir iniciar a próxima batalha?");
            partida.setStatus(StatusPartida.AGUARDANDO_PROMPT_REINICIAR);
            botaoAcao.setText("Reiniciar");

            oceanoAmigo.setDisable(true);
            oceanoInimigo.setDisable(true);
            botaoAcao.setDisable(false);

            return;
        }


        informar("Embarcação inimiga atingida!");

        // Bot atira
        botaoAcao.setDisable(true);
        playAnimacaoTiroBot();

        // Se o bot ganhou o jogo...
        if (partida.partidaAcabou()) {

            System.out.println("Partida acabou: bot vence");

            PauseTransition inicio = new PauseTransition(new Duration(0));
            inicio.setOnFinished(evt -> {
                statusJogo.setText("Hoje não foi seu dia...");
            });

            PauseTransition finalizacao = new PauseTransition(new Duration(3000));
            finalizacao.setOnFinished(evt -> {
                statusJogo.setText("Deseja tentar novamente?");
                botaoAcao.setText("Reiniciar");
            });

            SequentialTransition sequentialTransition = new SequentialTransition(inicio, finalizacao);
            sequentialTransition.play();



            partida.setStatus(StatusPartida.AGUARDANDO_PROMPT_REINICIAR);
            botaoAcao.setDisable(false);

        }

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
            Boolean acertouEmbarcacao = partida.turnoJogador(tiro);
            casaSelecionada.setStyle(acertouEmbarcacao ? EstiloCasa.EMBARCACAO_ATINGIDA : EstiloCasa.ATINGIDA_SEM_SUCESSO);
            botaoAcao.setDisable(true);
        });

        PauseTransition encerramento = new PauseTransition(new Duration(500));
        encerramento.setOnFinished(e -> {
            if (partida.partidaAcabou())
                statusJogo.setText("Travada a boa batalha. Deseja ir para a próxima?");
            else
                statusJogo.setText("Selecione uma casa para atirar");
        });

        SequentialTransition sequentialTransition = new SequentialTransition(inicio, preparar, apontar, fogo, encerramento);
        sequentialTransition.play();
    }

    public Boolean playAnimacaoTiroBot() throws InterruptedException {

        Casa casaAleatoria = partida.getJogador().getTabuleiro().getCasaSemtiroAleatoria();
        Pane paneAtingido = getNodeByRowColumnIndex(casaAleatoria.getI(), casaAleatoria.getJ(), oceanoAmigo);

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
            Boolean acertouEmbarcacao = partida.turnoBot();
            paneAtingido.setStyle(acertouEmbarcacao ? EstiloCasa.EMBARCACAO_ATINGIDA : EstiloCasa.ATINGIDA_SEM_SUCESSO);
            botaoAcao.setDisable(true);
        });

        PauseTransition encerramento = new PauseTransition(new Duration(0));
        encerramento.setOnFinished(e -> {
            if (!partida.partidaAcabou())
                statusJogo.setText("Selecione uma casa para atirar");
        });

        SequentialTransition sequentialTransition = new SequentialTransition(inicio, preparar, apontar, fogo, encerramento);
        sequentialTransition.play();

        return partida.partidaAcabou();
    }

    public static <T> T getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (T) node;
            }
        }
        return null;
    }

    public Boolean casaAtingida(Pane pane, Tabuleiro tabuleiro) {

        String id = casaSelecionada.getId();
        Integer i = Integer.parseInt(id.substring(id.length() - 1));
        Integer j = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));

        return tabuleiro.getCasa(i, j).getTemTiro() != null;
    }

    public Boolean casaOcupada(Pane pane, Tabuleiro tabuleiro) {
        String id = casaSelecionada.getId();
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

    private Boolean isEnemyCell(Pane pane) {
        return pane.getId().startsWith("enemyCell");
    }

    private Boolean isFriendCell(Pane pane) {
        return pane.getId().startsWith("friendCell");
    }

}
