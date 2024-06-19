package br.ufrn.imd.yeamanja.model;

import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;
import br.ufrn.imd.yeamanja.model.enumerations.TipoJogador;

/**
 * representa o jogador humano
 *
 * @author  ângelo barbosa
 */
public class JogadorHumano extends Jogador {

    public JogadorHumano(String nome) {
        super(nome);
        this.setTipo(TipoJogador.HUMANO);
    }

    /**
     * executa o turno do jogador humano
     * @param tiro tiro escolhido pelo jgoador
     * @param adversario alvo do tiro escolhido
     * @return
     */
    @Override
    public ResultadoTurno jogaTurno(Tiro tiro, Jogador adversario) {
        Casa casaAtacada = adversario.getTabuleiro().getCasa(tiro.getI(), tiro.getJ());
        casaAtacada.setTemTiro(tiro);
        Boolean embarcacaoAtingida = casaAtacada.getTemNavio() != null;

        return new ResultadoTurno(embarcacaoAtingida, casaAtacada);
    }
}
