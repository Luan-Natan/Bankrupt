import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.montarTabuleiro();
        tabuleiro.mostrarTabuleiro();

        var acoes = new Acoes(tabuleiro);


        Player jogador1 = new PlayerImpulsivo(1);
        Player jogador2 = new PlayerExigente(2);
        Player jogador3 = new PlayerCauteloso(3);
        Player jogador4 = new PlayerAleatorio(4);


        tabuleiro.cadastrarPlayers(jogador1,jogador2,jogador3,jogador4);

        acoes.acaoJogador(jogador1);
        acoes.acaoJogador(jogador2);
        acoes.acaoJogador(jogador3);
        acoes.acaoJogador(jogador4);



        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        tabuleiro.mostrarTabuleiro();

        tabuleiro.setQtdPartida(jogador3);
        tabuleiro.apurarResultados();

    }
}