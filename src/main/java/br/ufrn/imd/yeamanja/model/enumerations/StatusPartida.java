package br.ufrn.imd.yeamanja.model.enumerations;

/**
 * enumera os diferentes status da {@link br.ufrn.imd.yeamanja.model.Partida}
 *
 * @author  ângelo barbosa
 */
public enum StatusPartida {

    AGUARDANDO_SELECAO_CASA(false),
    AGUARDANDO_TIRO_JOGADOR(false),
    AGUARDANDO_TIRO_BOT(false),
    AGUARDANDO_PROMPT_REINICIAR(true),
    EXIBINDO_INFORMACAO_INGAME(false),
    EXIBINDO_INFORMACAO_OFFGAME(true),
    VITORIA_JOGADOR(true),
    VITORIA_BOT(true);

    StatusPartida(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    private Boolean finalizado;

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }
}
