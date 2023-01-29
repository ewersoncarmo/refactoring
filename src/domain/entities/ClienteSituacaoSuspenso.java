package domain.entities;

public class ClienteSituacaoSuspenso extends ClienteSituacao {

    @Override
    void rejeitada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PROCESSO_CANCELAMENTO
        super.rejeitada(cliente, cobranca);
    }

    @Override
    void cancelada(Cliente cliente, Cobranca cobranca) {
        // * ver o que faz com o cliente, pois se ele ficar suspenso não vai gerar nova fatura no próximo mês
        super.cancelada(cliente, cobranca);
    }

    @Override
    void efetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PROCESSO_CANCELAMENTO caso tenha pago parcial (cobrança parcial)
        super.efetivada(cliente, cobranca);
    }

    @Override
    void naoEfetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PROCESSO_CANCELAMENTO caso não gere cobrança parcial
        super.naoEfetivada(cliente, cobranca);
    }
}
