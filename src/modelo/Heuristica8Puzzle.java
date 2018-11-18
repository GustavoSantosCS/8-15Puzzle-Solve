package modelo;

/**
 * @author gusta
 */
public class Heuristica8Puzzle implements Heuristica {

    @Override
    public int getH1(int[][] emAnalise) {
        int aux = 8;
        if (emAnalise[0][0] != 1) {
            aux--;
        }
        if (emAnalise[0][1] != 2) {
            aux--;
        }
        if (emAnalise[0][2] != 3) {
            aux--;
        }
        if (emAnalise[1][0] != 4) {
            aux--;
        }
        if (emAnalise[1][1] != 5) {
            aux--;
        }
        if (emAnalise[1][2] != 6) {
            aux--;
        }
        if (emAnalise[2][0] != 7) {
            aux--;
        }
        if (emAnalise[2][1] != 8) {
            aux--;
        }
        return aux;
    }

    @Override
    public int getH2(int[][] emAnalise) {
        int aux = 0;
        for (int linha = 0; linha < emAnalise.length; linha++) {
            for (int coluna = 0; coluna < emAnalise.length; coluna++) {
                switch (emAnalise[linha][coluna]) {
                    case 1:
                        aux += (Math.abs(linha - 0) + Math.abs(coluna - 0));
                        break;

                    case 2:
                        aux += (Math.abs(linha - 0) + Math.abs(coluna - 1));
                        break;

                    case 3:
                        aux += (Math.abs(linha - 0) + Math.abs(coluna - 2));
                        break;

                    case 4:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 0));
                        break;

                    case 5:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 1));
                        break;

                    case 6:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 2));
                        break;

                    case 7:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 0));
                        break;

                    case 8:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 1));
                        break;
                }
            }
        }
        return aux;
    }

    @Override
    public int getH2P(int[][] emAnalise) {
        int[] po;
        //po[0] linha
        //po[1] coluna
        if (emAnalise[0][0] != 1) {
            po = getNumeroPosicao(1, emAnalise);
            return (4 - (Math.abs(0 - po[0]) + Math.abs(0 - po[1])));

        } else if (emAnalise[0][1] != 2) {
            po = getNumeroPosicao(2, emAnalise);
            return (2 - (Math.abs(0 - po[0]) + Math.abs(1 - po[1])));

        } else if (emAnalise[0][2] != 3) {
            po = getNumeroPosicao(3, emAnalise);
            return (4 - (Math.abs(0 - po[0]) + Math.abs(2 - po[1])));

        } else if (emAnalise[1][0] != 4) {
            po = getNumeroPosicao(4, emAnalise);
            return (3 - (Math.abs(1 - po[0]) + Math.abs(0 - po[1])));

        } else if (emAnalise[2][0] != 7) {
            po = getNumeroPosicao(7, emAnalise);
            return (4 - (Math.abs(2 - po[0]) + Math.abs(0 - po[1])));
        }
        return 10;
    }

    @Override
    public int[] getNumeroPosicao(int numero, int[][] emAnalise) {
        for (int linha = 0; linha < emAnalise.length; linha++) {
            for (int coluna = 0; coluna < emAnalise.length; coluna++) {
                if (emAnalise[linha][coluna] == numero) {
                    return new int[]{linha, coluna};
                }
            }
        }
        return new int[]{-1, -1};
    }

}
