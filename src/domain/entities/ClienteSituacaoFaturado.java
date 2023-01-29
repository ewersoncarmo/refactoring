package domain.entities;

import domain.events.domainevents.impl.CobrancaGeradaEventoDominio;
import domain.events.domainevents.impl.CobrancaNaoGeradaEventoDominio;

public class ClienteSituacaoFaturado extends ClienteSituacao {

    @Override
    public void clienteFaturado(Cliente cliente, Cobranca cobranca) {
        // gera cobrança como AGENDADA (total)
        cliente.adicionaEvento(CobrancaGeradaEventoDominio.builder(cliente, cobranca).build());
    }

    @Override
    public void erroFaturar(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para SUSPENSO
        cliente.adicionaEvento(CobrancaNaoGeradaEventoDominio.builder(cliente, cobranca).build());
    }

    @Override
    void rejeitada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PROCESSO_CANCELAMENTO
        super.rejeitada(cliente, cobranca);
    }

    @Override
    void cancelada(Cliente cliente, Cobranca cobranca) {
        // * ver o que faz com o cliente, pois se ele ficar faturado vai ser suspenso no próximo mês
        super.cancelada(cliente, cobranca);
    }

    @Override
    void efetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PAGO (pagamento total da cobrança original)
        super.efetivada(cliente, cobranca);
    }

    @Override
    void naoEfetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cliente para PROCESSO_CANCELAMENTO caso não gere cobrança parcial
        super.naoEfetivada(cliente, cobranca);
    }
}