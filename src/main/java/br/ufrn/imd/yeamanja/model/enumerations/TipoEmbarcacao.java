package br.ufrn.imd.yeamanja.model.enumerations;


/**
 * enumera os tipos de {@link br.ufrn.imd.yeamanja.model.Embarcacao}
 *
 * @author  ângelo barbosa
 */
public enum TipoEmbarcacao {

    CORVETA("Corveta", 2),
    SUBMARINO("Submarino", 3),
    FRAGATA("Fragata", 4),
    DESTROYER("Destroyer", 5);

    final String nome;
    final Integer tamanho;

    TipoEmbarcacao(String nome, Integer tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTamanho() {
        return tamanho;
    }
}
