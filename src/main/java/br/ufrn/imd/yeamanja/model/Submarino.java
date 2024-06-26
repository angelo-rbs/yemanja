package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

/**
 * repreesnta uma embarcação do tipo submarino
 *
 * @author ângelo barbosa
 */
public class Submarino extends Embarcacao {

    public Submarino(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.SUBMARINO;
    }

    public Submarino() {
        this.tipoEmbarcacao = TipoEmbarcacao.SUBMARINO;
    }
}
