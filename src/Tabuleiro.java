import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tabuleiro
{
    private int[][] casas;
    public Player[] players;
    private int qtdRodada;
    private int qtdPartida;

    private double mediaPartida;
    private int qtdTimeOut;
    private int totalRodada;

    public int[][] getCasas() {
        return casas;
    }

    // 0, 1, 2, 3
    // 1, 4, 3, 2

    // 0, 1, 2, 3
    // 3, 2, 4, 1


    //Cadastrar Players
    public void cadastrarPlayers(Player impulsivo, Player exigente, Player cauteloso, Player aleatorio)
    {
        this.players = new Player[]{impulsivo, exigente, cauteloso, aleatorio};

        //Arrays.sort(this.players);
    }


    //Metricas
    public int getQtdRodada() {
        return qtdRodada;
    }

    public void setQtdRodada() {
        this.qtdRodada += 1;
    }


    public int getQtdPartida() {
        return qtdPartida;
    }

    public void setQtdPartida(Player player) {
        this.qtdPartida += 1;

        setMediaPartida();

        player.setQtdVitoria();

        if(this.qtdRodada == 1000) this.qtdTimeOut += 1;
    }


    public double getMediaPartida() {
        return mediaPartida;
    }

    public void setMediaPartida() {
        this.totalRodada += this.qtdRodada;

        this.mediaPartida = (float)this.totalRodada / (float)this.qtdPartida;
    }

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
        System.out.println("Quantidade de partidas: " + getQtdPartida());
        System.out.println("Quantidade de rodadas: " + getQtdRodada());
        System.out.println("Média de rodadas de uma de partida: " + getMediaPartida());

        System.out.println("Média % vitórias Player impulsivo: " + (this.players[0].getQtdVitoria() / getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player exigente : " + (this.players[1].getQtdVitoria() / getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player cauteloso: " + (this.players[2].getQtdVitoria() / getQtdPartida()) * 100);
        System.out.println("Média % vitórias Player aleatório: " + (this.players[3].getQtdVitoria() / getQtdPartida()) * 100);

        System.out.println("Comportamento com mais vitórias: " + playerComMaisVitoria());
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
}
