package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

public class Destroyer extends Embarcacao {

    public Destroyer(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.DESTROYER;
    }

    public Destroyer() {
        this.tipoEmbarcacao = TipoEmbarcacao.DESTROYER;
    }
}
