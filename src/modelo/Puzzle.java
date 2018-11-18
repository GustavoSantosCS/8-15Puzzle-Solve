package modelo;

/**
 *
 * @author gusta
 */
public interface Puzzle {

    public long calcularFH1();

    public long calcularFH2Simples();

    public long calcularFH2RH();

    public void arrumarPZero();

    public void setLocalizacaoDoZero(int linha, int coluna);

    public int getZeroColuna();

    public int getZeroLinha();

    public Puzzle getPai();

    public void imprimir();

    public void setMatriz(int[][] matriz);

    public int[][] getMatriz();

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

    public Puzzle clone();

    public boolean isFinalized();

}
