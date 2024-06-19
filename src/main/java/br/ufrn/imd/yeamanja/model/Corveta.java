package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

/**
 * representa a embarcação de tipo fragata
 *
 * @author  ângelo barbosa
 */
public class Corveta extends Embarcacao {

    public Corveta(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.CORVETA;
    }

    public Corveta() {
        this.tipoEmbarcacao = TipoEmbarcacao.CORVETA;
    }
}
