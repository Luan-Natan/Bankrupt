import java.util.Random;

public class Acoes
{
    private Tabuleiro tabuleiro;
    public Acoes (Tabuleiro tabuleiro)
    {
        this.tabuleiro = tabuleiro;
    }

    public Acoes(){}


    //Movimentacao
    public void acaoJogador(Player player)
    {
        jogarDado(player);

        player.verificar(this.tabuleiro);

        this.tabuleiro.setQtdRodada();
    }

    private void jogarDado(Player player)
    {
        Random random = new Random();
        int faceDado = random.nextInt(6) + 1;

        andarCasas(faceDado, player);
    }

    private void andarCasas(int numeroCasas, Player player)
    {
        player.setPosicao(numeroCasas, this.tabuleiro);
    }
}
