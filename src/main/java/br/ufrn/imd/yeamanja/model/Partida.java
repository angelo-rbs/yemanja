package br.ufrn.imd.yeamanja.model;

public class Jogo {

    Jogador player, adversario;


    public Jogador getUsuarioVitorioso() {

        Jogador usuarioVencedor = null;

        if (player.venceu()) usuarioVencedor = player;
        else if (adversario.venceu()) usuarioVencedor = adversario;

        return usuarioVencedor;
    }

}
