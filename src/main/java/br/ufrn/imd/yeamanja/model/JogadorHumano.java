package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;

public class JogadorHumano extends Jogador {

    public JogadorHumano(String nome) {
        super(nome);
        this.setTipo(TipoJogador.HUMANO);
    }

    @Override
    public Boolean jogaTurno(Tiro tiro, Jogador adversario) {
        Casa casa = adversario.getTabuleiro().getCasa(tiro.getI(), tiro.getJ());
        casa.setTemTiro(tiro);
        return casa.getTemNavio() != null;
    }
}
