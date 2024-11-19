public class Player extends Acoes
{
    private int posicao;
    private int saldo;
    private int identificacao;
    private int qtdVitoria;


    //Construtores
    public Player(int identificacao) {
        this.posicao = 0;
        this.saldo = 300;
        this.identificacao = identificacao;
    }

    public Player(){ };


    //Metodos das propriedades
    public int getIdentificacao()
    {
        return identificacao;
    }

    public int getQtdVitoria() {
        return qtdVitoria;
    }

    public void setQtdVitoria() {
        this.qtdVitoria += 1;
    }


    //Metodos a serem sobrecarregados
    public void verificar(Tabuleiro tabuleiro){}

    public String getComportamento() { return "";}


    //Recuperar e alocar posicao do player no tabuleiro
    public int getPosicao()
    {
        return posicao;
    }

    public void setPosicao(int posicao, Tabuleiro tabuleiro)
    {
        int tamanhoTabuleiro = tabuleiro.getCasas().length;
        int posicaoFutura = this.posicao + posicao;

        if(posicaoFutura >= tamanhoTabuleiro)
        {
            this.posicao = posicaoFutura - tamanhoTabuleiro;

           //quando o player completar uma volta no tabuleiro, somar 100 de saldo
            this.somarSaldo(100);
        }

        this.posicao += posicao;
    }


    //Busca
    public Player buscarPlayer(int identificacao, Tabuleiro tabuleiro)
    {
        Player playerEncontrado = new Player();

        for (int i = 0; i < tabuleiro.players.length; i++) {
            if (tabuleiro.players[i].getIdentificacao() == identificacao) playerEncontrado = tabuleiro.players[i];
        }

        return playerEncontrado;
    }


    //Operacoes com Saldo
    public int getSaldo() {
        return saldo;
    }

    public void somarSaldo(int saldo) {
        this.saldo += saldo;
    }

    public void subtrairSaldo(int saldo) {
        this.saldo -= saldo;
    }


    //Compra
    public void comprarCasa(Player player, Tabuleiro tabuleiro)
    {
        int posicao = player.getPosicao();
        int valorCasa = tabuleiro.getValorCasa(posicao);

        if (valorCasa <= player.getSaldo())
        {
            tabuleiro.setDonoCasa(posicao, player);

            player.subtrairSaldo(valorCasa);
        }
    }


    //Aluguel
    public boolean pagarAluguel(Player player, Tabuleiro tabuleiro)
    {
        int posicao = player.getPosicao();
        int donoCasa = tabuleiro.getDonoCasa(posicao);
        int valorAluguel = tabuleiro.getAluguelCasa(posicao);

        if(donoCasa > 0)
        {
            var playerAluguel = this.buscarPlayer(donoCasa, tabuleiro);
            playerAluguel.somarSaldo(valorAluguel);

            player.subtrairSaldo(valorAluguel);

            return true;
        }

        return false;
    }
}
