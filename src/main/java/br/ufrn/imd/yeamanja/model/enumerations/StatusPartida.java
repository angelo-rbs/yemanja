package br.ufrn.imd.yeamanja.model.enumerations;

public enum StatusPartida {

    AGUARDANDO_SELECAO_CASA(false),
    AGUARDANDO_TIRO_JOGADOR(false),
    AGUARDANDO_TIRO_BOT(false),
    AGUARDANDO_PROMPT_REINICIAR(true),
    EXIBINDO_INFORMACAO_INGAME(false),
    EXIBINDO_INFORMACAO_OFFGAME(true);

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
