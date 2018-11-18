package modelo;

/**
 * @author gusta
 */
public class Heuristica15Puzzle implements Heuristica {

    @Override
    public int getH1(int[][] emAnalise) {
        int aux = 15;
        if (emAnalise[0][0] != 1) {
            aux--;
        }
        if (emAnalise[0][1] != 2) {
            aux--;
        }
        if (emAnalise[0][2] != 3) {
            aux--;
        }
        if (emAnalise[0][3] != 4) {
            aux--;
        }
        if (emAnalise[1][0] != 5) {
            aux--;
        }
        if (emAnalise[1][1] != 6) {
            aux--;
        }
        if (emAnalise[1][2] != 7) {
            aux--;
        }
        if (emAnalise[1][3] != 8) {
            aux--;
        }
        if (emAnalise[2][0] != 9) {
            aux--;
        }
        if (emAnalise[2][1] != 10) {
            aux--;
        }
        if (emAnalise[2][2] != 11) {
            aux--;
        }
        if (emAnalise[2][3] != 12) {
            aux--;
        }
        if (emAnalise[3][0] != 13) {
            aux--;
        }
        if (emAnalise[3][1] != 14) {
            aux--;
        }
        if (emAnalise[3][2] != 15) {
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
                        aux += (Math.abs(linha - 0) + Math.abs(coluna - 3));
                        break;

                    case 5:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 0));
                        break;

                    case 6:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 1));
                        break;

                    case 7:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 2));
                        break;

                    case 8:
                        aux += (Math.abs(linha - 1) + Math.abs(coluna - 3));
                        break;

                    case 9:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 0));
                        break;

                    case 10:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 1));
                        break;

                    case 11:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 2));
                        break;

                    case 12:
                        aux += (Math.abs(linha - 2) + Math.abs(coluna - 3));
                        break;

                    case 13:
                        aux += (Math.abs(linha - 3) + Math.abs(coluna - 0));
                        break;

                    case 14:
                        aux += (Math.abs(linha - 3) + Math.abs(coluna - 1));
                        break;

                    case 15:
                        aux += (Math.abs(linha - 3) + Math.abs(coluna - 2));
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
            return (6 - (Math.abs(0 - po[0]) + Math.abs(0 - po[1])));

        } else if (emAnalise[0][1] != 2) {
            po = getNumeroPosicao(2, emAnalise);
            return (5 - (Math.abs(0 - po[0]) + Math.abs(1 - po[1])));

        } else if (emAnalise[0][2] != 3) {
            po = getNumeroPosicao(3, emAnalise);
            return (5 - (Math.abs(0 - po[0]) + Math.abs(2 - po[1])));

        } else if (emAnalise[0][3] != 4) {
            po = getNumeroPosicao(4, emAnalise);
            return (5 - (Math.abs(0 - po[0]) + Math.abs(3 - po[1])));

        } else if (emAnalise[1][0] != 5) {
            po = getNumeroPosicao(5, emAnalise);
            return (5 - (Math.abs(1 - po[0]) + Math.abs(0 - po[1])));

        } else if (emAnalise[2][0] != 9) {
            po = getNumeroPosicao(9, emAnalise);
            return (5 - (Math.abs(2 - po[0]) + Math.abs(0 - po[1])));

        } else if (emAnalise[3][0] != 13) {
            po = getNumeroPosicao(13, emAnalise);
            return (6 - (Math.abs(3 - po[0]) + Math.abs(0 - po[1])));
        }
        return 15;
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
