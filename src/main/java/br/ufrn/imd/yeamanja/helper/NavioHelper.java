package br.ufrn.imd.yeamanja.helper;

import br.ufrn.imd.yeamanja.model.Casa;
import br.ufrn.imd.yeamanja.model.Embarcacao;
import br.ufrn.imd.yeamanja.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class NavioHelper {

    public List<Casa> getCasasOcupadas(Tabuleiro tabuleiro, Embarcacao embarcacao) {

        List<Casa> casasOcupadas = new ArrayList<>();
        Casa casaOcupada = embarcacao.getCasaPivot();
        casasOcupadas.add(casaOcupada);

        Integer quantCasasOcupadas = embarcacao.getCumprimentoEmCasas();

        while (quantCasasOcupadas-- > 0) {
            casaOcupada = tabuleiro.getProximaCasa(casaOcupada, embarcacao.getOrientacao());
            casasOcupadas.add(casaOcupada);
        }

        casasOcupadas = tabuleiro.getCasasOcupadasPorEmbarcacao(embarcacao);

        return casasOcupadas;
    }

    public Boolean foiDestruido(Tabuleiro tabuleiro, Embarcacao embarcacao) {
        return getCasasOcupadas(tabuleiro, embarcacao)
                .stream().allMatch(casa -> casa.getTemTiro() != null);
    }
}
