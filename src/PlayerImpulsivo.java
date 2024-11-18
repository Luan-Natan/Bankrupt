public class PlayerImpulsivo extends Player
{
    public PlayerImpulsivo(int identificacao) {
        super(identificacao);
    }

    //Jogada
    @Override
    public void verificar(Tabuleiro tabuleiro)
    {
        if(!pagarAluguel(this, tabuleiro))
            comprarCasa(this, tabuleiro);
    }

    @Override
    public String getComportamento()
    {
       return "Impulsivo";
    }
}
