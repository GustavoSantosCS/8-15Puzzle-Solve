package modelo;

/**
 *
 * @author gusta
 */
public interface Heuristica {
    public int getH1(int[][] emAnalise);
    public int getH2(int[][] emAnalise);
    public int getH2P(int[][] emAnalise);
    public int[] getNumeroPosicao(int numero, int[][] emAnalise);
}
