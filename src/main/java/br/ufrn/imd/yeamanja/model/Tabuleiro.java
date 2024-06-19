package br.ufrn.imd.yeamanja.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import br.ufrn.imd.yeamanja.model.enumerations.Orientacao;

import static br.ufrn.imd.yeamanja.model.Constante.DIMENSAO_TABULEIRO;
import static br.ufrn.imd.yeamanja.model.enumerations.Orientacao.*;

/**
 * representa o tabuleiro com os barcos de um jogador
 *
 * @author ângelo barbosa
 */
public class Tabuleiro {

    private Casa[][] casas = new Casa[DIMENSAO_TABULEIRO][DIMENSAO_TABULEIRO];
    private List<Embarcacao> embarcacoes;
    private Boolean populado = false;

    // métodos funcionais

    /**
     * popula o tabuleiro de embarcações
     * @param embarcacoesParaPopular lista de embarcações para adicionar no tabuleiro
     */
    public void popular(List<Embarcacao> embarcacoesParaPopular) {
        embarcacoesParaPopular.forEach(embarcacao -> {
            posicionarEmbarcacao(embarcacao);
        });

        embarcacoes = embarcacoesParaPopular;
        populado = true;
    }

    /**
     * adiciona no tabuleiro uma embarcação numa posição livre aletória
     * @param embarcacao embarcação a ser posicionada
     */
    public void posicionarEmbarcacao(Embarcacao embarcacao) {

        PosicaoEmbarcacao posicao = encontrarPosicaoLivreParaEmbarcacao(embarcacao);
        List<Casa> casasOcupadas = getSequenciaCasas(posicao.getCasaPivot(), posicao.getOrientacao(), embarcacao.getCumprimentoEmCasas() - 1);

        casasOcupadas.forEach(casa -> casa.setTemNavio(embarcacao));
        embarcacao.setCasasOcupadas(casasOcupadas);
        embarcacao.setPosicao(posicao);
    }

    /**
     * encontra uma posição livre no tabuleiro em que a embarcação dada cabe
     * @param embarcacao embarcação a ser verificada
     * @return posição livre
     */
    public PosicaoEmbarcacao encontrarPosicaoLivreParaEmbarcacao(Embarcacao embarcacao) {

        Boolean achouPosicao = false;
        Casa possivelPivot = null;
        Orientacao possivelOrientacao = null;


        while (!achouPosicao) {
            possivelPivot = getCasaSemNavioAleatoria();
            possivelOrientacao = getOrientacaoAleatoria();

            achouPosicao = embarcacaoCabeNaPosicao(embarcacao, possivelPivot, possivelOrientacao);
        }

        return new PosicaoEmbarcacao(possivelPivot, possivelOrientacao);
    }

    /**
     * verifica se uma embarcação cabe na posição dada
     * @param embarcacao embarcação a ser verificada
     * @param possivelPivot casa pivô da posição da embarcação
     * @param possivelOrientacao orientação da sequência de casas a partir do pivot a serem ocupadas
     * @return
     */
    public Boolean embarcacaoCabeNaPosicao(Embarcacao embarcacao, Casa possivelPivot, Orientacao possivelOrientacao) {
        List<Casa> casasDaPosicao = getSequenciaCasas(possivelPivot, possivelOrientacao, embarcacao.getCumprimentoEmCasas() - 1);
        if (casasDaPosicao == null) return false;
        return casasDaPosicao.stream()
                .allMatch(possivelCasaOcupada -> possivelCasaOcupada.getTemNavio() == null);
    }

    /**
     * encontra e retorna as casas que uma embarcação ocupa
     * @param embarcacao embarcação ocupante
     * @return lista de casas ocupadas pela embarcação
     */
    public List<Casa> getCasasOcupadasPorEmbarcacao(Embarcacao embarcacao) {
        return getSequenciaCasas(embarcacao.getCasaPivot(), embarcacao.getOrientacao(), embarcacao.getCumprimentoEmCasas() - 1);
    }

    /**
     * encontra e retorna a lista de todas as casas ocupada pelas embarcações dadas
     * @param embarcacoes embarcações ocupantes
     * @return lista de todas as casas ocupadas
     */
    public List<Casa> getCasasOcupadasPorEmbarcacao(List<Embarcacao> embarcacoes) {

        List<Casa> casasOcupadas = new ArrayList<>();
        embarcacoes.forEach(embarcacao -> casasOcupadas.addAll(getCasasOcupadasPorEmbarcacao(embarcacao)));

        return casasOcupadas;
    }


    /**
     * calcula e retorna a sequência de casas do tabuleiro correspondente aos parâmetros dados
     * @param casaPivot casa que inicia a sequência
     * @param orientacao direção da sequência a partir da casa pivot
     * @param tamanhoSequencia - quantidade de casas da sequência INCLUINDO a casa pivot
     * @return
     */
    public List<Casa> getSequenciaCasas(Casa casaPivot, Orientacao orientacao, Integer tamanhoSequencia) {

        // valida se a sequência está dentro do tabuleiro

        if (!casaPivot.isDentroDoTabuleiro()) return null;

        Boolean respeitaTabuleiro = switch (orientacao) {
            case NORTE -> casaPivot.getI() - (tamanhoSequencia) >= 0;
            case SUL -> casaPivot.getI() + (tamanhoSequencia) < DIMENSAO_TABULEIRO;
            case LESTE -> casaPivot.getJ() + (tamanhoSequencia) < DIMENSAO_TABULEIRO;
            case OESTE -> casaPivot.getJ() - (tamanhoSequencia) >= 0;
        };
        if (!respeitaTabuleiro) return null;


        // segue calculo da sequẽncia

        Integer counter = tamanhoSequencia;
        List<Casa> sequencia = new ArrayList<>();
        Casa casa = casaPivot;
        sequencia.add(casa);

        while (counter-- > 0) {
            casa = this.getProximaCasa(casa, orientacao);
            sequencia.add(casa);
        }

        return sequencia;
    }

    /**
     * calcula e retorna a casa vizinha à casa dada na direção determinada
     * @param casa casa de referência
     * @param orientacao em qual direção se encontra a casa vizinha procurada em relação à referência
     * @return casa vizinha
     */
    public Casa getProximaCasa(Casa casa, Orientacao orientacao) {

        // não pode avançar fora do tabuleiro
        if ((casa.getI() == 0 && orientacao.equals(NORTE))
                || (casa.getJ() == DIMENSAO_TABULEIRO - 1 && orientacao.equals(Orientacao.LESTE))
                || (casa.getI() == DIMENSAO_TABULEIRO - 1 && orientacao.equals(Orientacao.SUL))
                || (casa.getJ() == 0 && orientacao.equals(Orientacao.OESTE)))
            return null;

        return switch (orientacao) {
            case NORTE -> casas[casa.getI() - 1][casa.getJ()];
            case SUL -> casas[casa.getI() + 1][casa.getJ()];
            case LESTE -> casas[casa.getI()][casa.getJ() + 1];
            case OESTE -> casas[casa.getI()][casa.getJ() - 1];
        };
    }

    /**
     * encontra uma casa não ocupada por embarcações aleatória
     * @return casa disponível
     */
    public Casa getCasaSemNavioAleatoria() {

        Integer i, j;
        Boolean isCasaDisponivel = false;
        Casa casaDisponivel = null;


        while (!isCasaDisponivel) {
            i = ThreadLocalRandom.current().nextInt(0, DIMENSAO_TABULEIRO);
            j = ThreadLocalRandom.current().nextInt(0, DIMENSAO_TABULEIRO);

            casaDisponivel = casas[i][j];
            isCasaDisponivel = casaDisponivel.getTemNavio() == null;
        }

        return casaDisponivel;
    }

    /**
     * calcula e retorna uma orientação/direção aleatória
     * @return a direção aleatória
     */
    public Orientacao getOrientacaoAleatoria() {

        Orientacao orientacoes[] = { NORTE, SUL, LESTE, OESTE };
        Integer index = ThreadLocalRandom.current().nextInt(0, 4);

        return orientacoes[index];
    }


    /**
     * cria o tubuleiro, suas cass, popula de embarcações e imprime seu esquema na saída padrão do sistema
     */
    public Tabuleiro() {
        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {
                casas[i][j] = new Casa(i, j);
            }
        }

        populado = false;

        List<Embarcacao> embarcacoes = new ArrayList<Embarcacao>(List.of(new Destroyer(), new Fragata(), new Submarino(), new Corveta()));
        popular(embarcacoes);
        print();
    }

    /**
     * imprime o tabuleiro na saída padrão do sistema
     */
    public void print() {
        for (int i = 0; i < DIMENSAO_TABULEIRO; i++) {
            for (int j = 0; j < DIMENSAO_TABULEIRO; j++) {

                if (casas[i][j].getTemNavio() != null) {

                    if (casas[i][j].getTemTiro() != null)
                        System.out.print("X");
                    else
                        System.out.print("O");
                } else{
                    if (casas[i][j].getTemTiro() != null)
                        System.out.print("S");
                    else
                        System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // métodos de acesso

    /**
     * retorna uma casa dada
     * @param i posição i (equivalente a x)
     * @param j posição j (equivalente a y)
     * @return casa referida
     */
    public Casa getCasa(int i, int j) {
        return casas[i][j];
    }

    public Boolean getPopulado() {
        return populado;
    }

    public void setPopulado(Boolean populado) {
        this.populado = populado;
    }

    public Casa[][] getCasas() {
        return casas;
    }

    public void setCasas(Casa[][] casas) {
        this.casas = casas;
    }

    public List<Embarcacao> getEmbarcacoes() {
        return embarcacoes;
    }

    public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
        this.embarcacoes = embarcacoes;
    }
}
