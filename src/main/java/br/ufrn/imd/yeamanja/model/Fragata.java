package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

/**
 * representa a embarcação de tipo fragata
 *
 * @author  ângelo barbosa
 */
public class Fragata extends Embarcacao {

    public Fragata(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.FRAGATA;
    }

    public Fragata() {
        this.tipoEmbarcacao = TipoEmbarcacao.FRAGATA;
    }
}
