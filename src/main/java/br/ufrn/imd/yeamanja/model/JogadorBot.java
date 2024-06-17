package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;

public class JogadorBot extends Jogador {


    public JogadorBot(String nome) {
        super(nome);
        setTipo(TipoJogador.BOT);
    }

    @Override
    public Boolean jogaTurno(Tiro tiro, Jogador adversario) {

        Casa casaAtingida = adversario.getTabuleiro().getCasa(tiro.getI(), tiro.getJ());
        casaAtingida.setTemTiro(tiro);

        return casaAtingida.getTemNavio() != null;
    }
}
