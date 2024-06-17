package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

public class Corveta extends Embarcacao {

    public Corveta(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.CORVETA;
    }

    public Corveta() {
        this.tipoEmbarcacao = TipoEmbarcacao.CORVETA;
    }
}
