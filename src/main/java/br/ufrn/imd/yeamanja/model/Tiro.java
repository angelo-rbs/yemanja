package br.ufrn.imd.yeamanja.model;

import javafx.util.Pair;

/**
 * classe que representa o tiro que é dado pelos jogadores em cada turno
 *
 * @author ângelo barbosa
 */
public class Tiro {

    Pair<Integer, Integer> coordinates = null;

    public Tiro(Integer i, Integer j) {
        coordinates = new Pair<>(i, j);
    }

    /**
     * retorna a coordenada i (equivalente a x) do tiro
     * @return
     */
    public Integer getI() {
        return coordinates.getKey();
    }

    /**
     * retorna a coordenada j (equivalende a y) do tiro
     * @return
     */
    public Integer getJ() {
        return coordinates.getValue();
    }
}
