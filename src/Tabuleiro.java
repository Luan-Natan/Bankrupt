import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tabuleiro
{
    private int[][] casas;
    public Player[] players;
    private int qtdRodada;

    private int qtdPartida = 0;
    private int qtdRodadaLimite = 1000;
    private int qtdPartidaLimite = 300;


    private double mediaPartida;
    private int qtdTimeOut;
    private int totalRodada;


    public int[][] getCasas() {
        return casas;
    }


    //Cadastrar Players
    public void cadastrarPlayers(Player impulsivo, Player exigente, Player cauteloso, Player aleatorio)
    {
        this.players = new Player[]{impulsivo, exigente, cauteloso, aleatorio};

        Collections.shuffle(Arrays.asList(this.players));
    }



    //Metricas
    public int getQtdRodada() {
        return qtdRodada;
    }

    public void setQtdRodada() {
        this.qtdRodada += 1;
    }

    public int getQtdRodadaLimite() {
        return qtdRodadaLimite;
    }

    public int getQtdPartidaLimite() {
        return qtdPartidaLimite;
    }

    public int getQtdPartida() {
        return qtdPartida;
    }

    public void setQtdPartida(Player player) {
        this.qtdPartida += 1;

        setMediaPartida();

        player.setQtdVitoria();

        if(this.qtdRodada == this.qtdRodadaLimite) this.qtdTimeOut += 1;
    }

    public double getMediaPartida() {
        return mediaPartida;
    }

    public void setMediaPartida() {
        this.totalRodada += this.qtdRodada;

        this.mediaPartida = (float)this.totalRodada / this.qtdPartida;
    }


    //Resultados
    private String playerComMaisVitoria()
    {
        int maior = 0;

        for (int i = 0; i < this.players.length; i++)
        {
            if (i == 0) continue;

            if(this.players[i].getQtdVitoria() > this.players[i-1].getQtdVitoria() && this.players[i].getQtdVitoria() > this.players[maior].getQtdVitoria())
                maior = i;
        }

        return this.players[maior].getComportamento();
    }

    public void apurarResultados()
    {
        System.out.println("\nQuantidade de partidas por timeout: " + this.qtdTimeOut);
        System.out.println("Média de rodadas de uma de partida: " + this.getMediaPartida());

        System.out.println("\nMédia % vitórias Player " + this.players[0].getComportamento() + ": " + ((float)this.players[0].getQtdVitoria() / this.getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player " + this.players[1].getComportamento() + ": " + ((float)this.players[1].getQtdVitoria() / this.getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player " + this.players[2].getComportamento() + ": " + ((float)this.players[2].getQtdVitoria() / this.getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player " + this.players[3].getComportamento() + ": " + ((float)this.players[3].getQtdVitoria() / this.getQtdPartida()) * 100);

        System.out.println("\nComportamento com mais vitórias: " + playerComMaisVitoria());
    }



    //Casas
    public int getValorCasa(int posicao)
    {
        for (int i = 0; i < this.casas.length; i++) {
            if(i == posicao) return this.casas[i][0];
        }

        return 0;
    }

    public int getAluguelCasa(int posicao)
    {
        for (int i = 0; i < this.casas.length; i++) {
            if(i == posicao) return this.casas[i][1];
        }

        return 0;
    }

    public int getDonoCasa(int posicao)
    {
        for (int i = 0; i < this.casas.length; i++) {
            if(i == posicao) return this.casas[i][2];
        }

        return 0;
    }

    public void setDonoCasa(int posicao, Player player)
    {
        for (int i = 0; i < this.casas.length; i++) {
            if(i == posicao) this.casas[i][2] = player.getIdentificacao();
        }
    }



    //Tabuleiro
    public void montarTabuleiro()
    {
        var conteudo = new ArrayList<String>();
        var path = ".\\gameConfig.txt";

        this.casas = new int[20][3];
        this.qtdRodada = 0;

        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            while ((str = in.readLine()) != null) {
                conteudo.add(str.trim());
            }
            in.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        for (int i = 0; i < 20; i++) {
            var linha = conteudo.get(i).split(" ");

            for (int j = 0; j < 3; j++) {
                this.casas[i][j] = j == 2 ? 0 : Integer.parseInt(linha[j]);
            }
        }
    }

    public void mostrarTabuleiro()
    {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.casas[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public Player verificarResultadoPartida()
    {
        List<Player> playersFinalPartida = Arrays.stream(this.players)
                .filter(player -> player.getSaldo() > 0)
                .toList();

        if (this.qtdRodada == this.getQtdRodadaLimite()) return playersFinalPartida.get(0);

        if (playersFinalPartida.size() == 1) return playersFinalPartida.get(0);

        return null;
    }
}