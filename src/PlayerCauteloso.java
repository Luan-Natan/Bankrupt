public class PlayerCauteloso extends Player
{
    public PlayerCauteloso(int identificacao) {
        super(identificacao);
    }

    //Jogada
    @Override
    public void verificar(Tabuleiro tabuleiro)
    {
        int posicao = this.getPosicao();
        int valorCasa = tabuleiro.getValorCasa(posicao);
        int saldoFinal = this.getSaldo() - valorCasa;

        if(!pagarAluguel(this, tabuleiro) && saldoFinal >= 80)
            comprarCasa(this, tabuleiro);
    }

    @Override
    public String getComportamento()
    {
        return "Cauteloso";
    }
}
