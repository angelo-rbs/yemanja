package br.ufrn.imd.yeamanja.model;

/**
 * aglutina os diferentes estilos em CSS das {@link Casa} do {@link Tabuleiro}
 */
public class EstiloCasa {

    public static final String CASA_INIMIGA_SELECIONADA = "-fx-border-color: #D0D02B; -fx-border-width: 5px; -fx-background-color: #0066ff;";
    public static final String CASA_AMIGA_SELECIONADA = "-fx-border-color: #4CDE25FF; -fx-border-width: 5px; -fx-background-color: #0066ff;";
    public static final String NORMAL = "-fx-border-color: #0066FF; -fx-background-color: #0066ff; -fx-border-width: 5px;";
    public static final String OCUPADA_POR_NAVIO = "-fx-background-color: #BED2EFFF; -fx-border-style: none;";
    public static final String OCUPADA_POR_NAVIO_SELECIONADA = "-fx-background-color: #BED2EFFF; -fx-border-color: #4CDE25FF; -fx-border-width: 5px;";
    public static final String ATINGIDA_SEM_SUCESSO = "-fx-background-color: #653737FF; -fx-border-style: none;";
    public static final String ATINGIDA_SEM_SUCESSO_AMIGA_SELECIONADA = "-fx-background-color: #653737FF; -fx-border-color: #4CDE25FF; -fx-border-width: 5px;";
    public static final String ATINGIDA_SEM_SUCESSO_INIMIGA_SELECIONADA = "-fx-background-color: #653737FF; -fx-border-color: #D0D02B; -fx-border-width: 5px;";
    public static final String EMBARCACAO_ATINGIDA = "-fx-background-color: #BE1C1F; -fx-border-style: none;";
    public static final String EMBARCACAO_ATINGIDA_AMIGA_SELECIONADA = "-fx-background-color: #BE1C1F; -fx-border-color: #4CDE25FF; -fx-border-width: 5px;";
    public static final String EMBARCACAO_ATINGIDA_INIMIGA_SELECIONADA = "-fx-background-color: #BE1C1F; -fx-border-color: #D0D02B; -fx-border-width: 5px;";

}

