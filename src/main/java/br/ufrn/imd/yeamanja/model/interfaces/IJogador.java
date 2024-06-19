package br.ufrn.imd.yeamanja.model.interfaces;

import br.ufrn.imd.yeamanja.model.Jogador;
import br.ufrn.imd.yeamanja.model.Tiro;
import br.ufrn.imd.yeamanja.model.dto.ResultadoTurno;

public interface IJogador  {

    ResultadoTurno jogaTurno(Tiro tiro, Jogador adversario);

}
