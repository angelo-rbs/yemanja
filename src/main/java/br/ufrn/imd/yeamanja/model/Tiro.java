package br.ufrn.imd.yeamanja.model;

import javafx.util.Pair;

public class Tiro {

    Pair<Integer, Integer> coordinates = null;

    public Tiro(Integer i, Integer j) {
        coordinates = new Pair<>(i, j);
    }

    public Integer getI() {
        return coordinates.getKey();
    }

    public Integer getJ() {
        return coordinates.getValue();
    }
}
