package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;
import br.ufrn.imd.yeamanja.model.enumerations.TipoEmbarcacao;

public class Submarino extends Embarcacao {


    public Submarino(Casa casaPivot, Orientacao orientacao) {
        super(casaPivot, orientacao);
        this.tipoEmbarcacao = TipoEmbarcacao.SUBMARINO;
    }

    public Submarino() {
        this.tipoEmbarcacao = TipoEmbarcacao.SUBMARINO;
    }
}
