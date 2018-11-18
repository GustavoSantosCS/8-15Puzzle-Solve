package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import modelo.Heuristica;
import modelo.Heuristica15Puzzle;
import modelo.Heuristica8Puzzle;
import modelo.Puzzle;
import modelo.Puzzle15;
import modelo.Puzzle8;

/**
 * @author gusta
 */
public class ClassePrincipal {

    public static Queue<Puzzle> lista;
    public static Set<Puzzle> jVisitados = new HashSet<>();
    public static Puzzle solucao;
    public static Heuristica h;

    public static void setEstrategia(Scanner input) {
        System.out.println("");
        System.out.println("Indique a Extrategia!");
        System.out.println("1 -  h1 - Total de peças fora do lugar");
        System.out.println("2 -  h2 - Distância de Manhattan (Distância da peça sem contar diagonais)");
        System.out.println("3 -  h2 + RH - Distância de Manhattan Com Estrategia de Richard Hayes");
        int i = input.nextInt();
        if (i == 1) {
            lista = new PriorityQueue<>(100, getNodoComparatorFH1());
        }
        if (i == 2) {
            lista = new PriorityQueue<>(100, getNodoComparatorFH2());
        }
        if (i == 3) {
            lista = new PriorityQueue<>(100, getNodoComparatorFH2ComEstrategia());
        }
    }

    public static Puzzle inicia() {
        Scanner input = new Scanner(System.in);
        System.out.println("######## Solve Puzzle ########");
        System.out.println("Qual Modelo de Puzzle?");
        System.out.println("1 - 8 Puzzle");
        System.out.println("2 - 15 Puzzle");
        System.out.println("3 - Sair");
        int a = input.nextInt();
        if (a == 1) {
            System.out.println("");
            h = new Heuristica8Puzzle();
            int[][] estado = new int[3][3];
            for (int linha = 0; linha < estado.length; linha++) {
                for (int coluna = 0; coluna < estado.length; coluna++) {
                    System.out.printf("Informa o Elemento[%s][%s]:  ", linha, coluna);
                    estado[linha][coluna] = input.nextInt();
                }
            }
            setEstrategia(input);
            return new Puzzle8(estado, new Puzzle8(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, null, 0, 0, 0, h), 0, 0, 0, h);
        }
        if (a == 2) {
            System.out.println("");
            h = new Heuristica15Puzzle();
            int[][] estado = new int[4][4];
            for (int linha = 0; linha < estado.length; linha++) {
                for (int coluna = 0; coluna < estado.length; coluna++) {
                    System.out.printf("Informa o Elemento[%s][%s]:  ", linha, coluna);
                    estado[linha][coluna] = input.nextInt();
                }
            }
            setEstrategia(input);
            input.close();
            return new Puzzle15(estado, new Puzzle15(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, null, 0, 0, 0, h), 0, 0, 0, h);
        }
        return null;
    }

    public static void main(String[] args) {
        Puzzle inicio = inicia();
        inicio.imprimir();
        lista.add(inicio);
        long init = Calendar.getInstance().getTimeInMillis();
        while (solucao == null) {
            if (gerarFilhos()) {
                break;
            }
        }

        Puzzle n = solucao;
        System.out.println("###### SOLUÇÃO ######");
        List<Puzzle> solucaoList = new ArrayList<>();
        while (n != null) {
            solucaoList.add(n);
            n = n.getPai();
        }

        for (int i = solucaoList.size() - 3; i > -1; i--) {
            if (i == 0) {
                System.out.printf("Fim");
            } else {
                System.out.printf("Passo %s", solucaoList.size() - (i + 2));
            }
            System.out.println("");
            solucaoList.get(i).imprimir();
        }
        System.out.println("Quantidade de Passos: " + (solucaoList.size() - 2));
        System.out.println("Quantidade de Nodos Visitados: " + jVisitados.size());
        System.out.println("Tempo Gasto: " + (Calendar.getInstance().getTimeInMillis() - init) + " ms");
    }

    public static boolean gerarFilhos() {
        Puzzle emAnalise = lista.remove();
        jVisitados.add(emAnalise);
        int linha = emAnalise.getZeroLinha();
        int coluna = emAnalise.getZeroColuna();
        if (coluna != 0) {
            Puzzle t = emAnalise.clone();
            int elemt = t.getMatriz()[linha][coluna - 1];
            t.getMatriz()[linha][coluna - 1] = 0;
            t.getMatriz()[linha][coluna] = elemt;
            t.setLocalizacaoDoZero(linha, coluna - 1);
            if (!jVisitados.contains(t)) {
                lista.add(t);
                if (t.isFinalized()) {
                    solucao = t;
                    return true;
                }
            }
        }

        if (coluna != emAnalise.getMatriz().length - 1) {
            Puzzle t = emAnalise.clone();
            int elemt = t.getMatriz()[linha][coluna + 1];
            t.getMatriz()[linha][coluna + 1] = 0;
            t.getMatriz()[linha][coluna] = elemt;
            t.setLocalizacaoDoZero(linha, coluna + 1);

            if (!jVisitados.contains(t)) {
                lista.add(t);
                if (t.isFinalized()) {
                    solucao = t;
                    return true;
                }
            }
        }

        if (linha != 0) {
            Puzzle t = emAnalise.clone();
            int elemt = t.getMatriz()[linha - 1][coluna];
            t.getMatriz()[linha - 1][coluna] = 0;
            t.getMatriz()[linha][coluna] = elemt;
            t.setLocalizacaoDoZero(linha - 1, coluna);

            if (!jVisitados.contains(t)) {
                lista.add(t);
                if (t.isFinalized()) {
                    solucao = t;
                    return true;
                }
            }
        }

        if (linha != emAnalise.getMatriz().length - 1) {
            Puzzle t = emAnalise.clone();
            int[][] mt = t.getMatriz();
            int elemt = mt[linha + 1][coluna];
            mt[linha + 1][coluna] = 0;
            mt[linha][coluna] = elemt;
            t.setMatriz(mt);
            t.setLocalizacaoDoZero(linha + 1, coluna);

            if (!jVisitados.contains(t)) {
                lista.add(t);
                if (t.isFinalized()) {
                    solucao = t;
                    return true;
                }
            }
        }
        return false;
    }

    static protected Comparator<Puzzle> getNodoComparatorFH1() {
        return (Puzzle no1, Puzzle no2) -> {
            long f1 = no1.calcularFH1();
            long f2 = no2.calcularFH1();
            if (f1 > f2) {
                return 1;
            } else if (f1 == f2) {
                return 0;
            } else {
                return -1;
            }
        };
    }

    static protected Comparator<Puzzle> getNodoComparatorFH2() {
        return (Puzzle no1, Puzzle no2) -> {
            long f1 = no1.calcularFH2Simples();
            long f2 = no2.calcularFH2Simples();
            if (f1 > f2) {
                return 1;
            } else if (f1 == f2) {
                return 0;
            } else {
                return -1;
            }
        };
    }

    static protected Comparator<Puzzle> getNodoComparatorFH2ComEstrategia() {
        return (Puzzle no1, Puzzle no2) -> {
            long f1 = no1.calcularFH2RH();
            long f2 = no2.calcularFH2RH();
            if (f1 > f2) {
                return 1;
            } else if (f1 == f2) {
                return 0;
            } else {
                return -1;
            }
        };
    }
}
