public class Main
{
    public static void main(String[] args)
    {
        Tabuleiro tabuleiro = new Tabuleiro();
        Acoes acoes = new Acoes(tabuleiro);

        Player jogador1 = new PlayerImpulsivo(1);
        Player jogador2 = new PlayerExigente(2);
        Player jogador3 = new PlayerCauteloso(3);
        Player jogador4 = new PlayerAleatorio(4);

        Player resultado = null;

        while (tabuleiro.getQtdPartida() < tabuleiro.getQtdPartidaLimite())
        {
            tabuleiro.montarTabuleiro();
            tabuleiro.cadastrarPlayers(jogador1,jogador2,jogador3,jogador4);

            resultado = null;

            while (resultado == null)
            {
                for (int i = 0; i < tabuleiro.players.length; i++)
                {
                    if(tabuleiro.players[i].getSaldo() < 0) continue;

                    if(tabuleiro.getQtdRodada() == tabuleiro.getQtdRodadaLimite()) break;

                    acoes.acaoJogador(tabuleiro.players[i]);
                }

                resultado = tabuleiro.verificarResultadoPartida();
            }

            tabuleiro.setQtdPartida(resultado);
        }

        tabuleiro.mostrarTabuleiro();
        tabuleiro.apurarResultados();
    }
}