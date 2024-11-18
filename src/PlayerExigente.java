public class PlayerExigente extends Player
{
    public PlayerExigente(int identificacao) {
        super(identificacao);
    }

    //Jogada
    @Override
    public void verificar(Tabuleiro tabuleiro)
    {
        int posicao = this.getPosicao();
        int valorAluguel = tabuleiro.getAluguelCasa(posicao);

        if(!pagarAluguel(this, tabuleiro) && valorAluguel > 50)
            comprarCasa(this, tabuleiro);
    }

    @Override
    public String getComportamento()
    {
        return "Exigente";
    }
}
