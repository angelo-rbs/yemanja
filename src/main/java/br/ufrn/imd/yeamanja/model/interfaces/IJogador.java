package br.ufrn.imd.yeamanja.model.interfaces;

import br.ufrn.imd.yeamanja.model.Jogador;
import br.ufrn.imd.yeamanja.model.Tiro;

public interface IJogador  {

    Boolean jogaTurno(Tiro tiro, Jogador adversario);

}
