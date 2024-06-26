package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;

/**
 * representa o jogador bot
 *
 * @author  ângelo barbosa
 */
public class JogadorBot extends Jogador {

    public JogadorBot(String nome) {
        super(nome);
        setTipo(TipoJogador.BOT);
    }

    @Override
    public ResultadoTurno jogaTurno(Tiro tiro, Jogador adversario) {

        Casa casaAtacada = adversario.getTabuleiro().getCasa(tiro.getI(), tiro.getJ());
        casaAtacada.setTemTiro(tiro);
        Boolean embarcacaoAtingida = casaAtacada.getTemNavio() != null;

        return new ResultadoTurno(embarcacaoAtingida, casaAtacada);
    }
}
