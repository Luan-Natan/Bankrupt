import java.util.Random;

public class PlayerAleatorio extends Player
{
    public PlayerAleatorio(int identificacao) {
        super(identificacao);
    }

    //Jogada
    @Override
    public void verificar(Tabuleiro tabuleiro)
    {
        Random random = new Random();
        int chanceCompra = random.nextInt(2);

        if(!pagarAluguel(this, tabuleiro) && chanceCompra == 1)
            comprarCasa(this, tabuleiro);
    }

    @Override
    public String getComportamento()
    {
        return "Aleatório";
    }
}