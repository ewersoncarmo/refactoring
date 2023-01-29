package domain.entities;

import domain.events.domainevents.impl.*;

public abstract class ClienteSituacao {

    void clienteFaturado(Cliente cliente, Cobranca cobranca) {}

    void erroFaturar(Cliente cliente, Cobranca cobranca) {}

    void acatada(Cliente cliente, Cobranca cobranca) {
        // comportamento comum para todas as situações
        // atualiza cobrança para PENDENTE
        cliente.adicionaEvento(CobrancaAcatadaEventoDominio.builder(cliente, cobranca).build());
    }

    void rejeitada(Cliente cliente, Cobranca cobranca) {
        // atualiza cobrança para REJEITADA
        // gera perda (valor integral)
        cliente.adicionaEvento(CobrancaRejeitadaEventoDominio.builder(cliente, cobranca).build());
    }

    void cancelada(Cliente cliente, Cobranca cobranca) {
        // atualiza cobrança para CANCELADA
        // gera perda (valor integral)
        cliente.adicionaEvento(CobrancaCanceladaEventoDominio.builder(cliente, cobranca).build());
    }

    void efetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cobrança para EFETIVADA
        // gera receita (valor integral caso tenha pago total ou valor parcial)
        // gera perda caso tenha pago parcial (valor parcial)
        cliente.adicionaEvento(CobrancaEfetivadaEventoDominio.builder(cliente, cobranca).build());
    }

    void naoEfetivada(Cliente cliente, Cobranca cobranca) {
        // atualiza cobrança para NAO_EFETIVADA
        // gera cobrança parcial como AGENDADA caso seja necessário, mantém cliente na mesma situação
        // gera perda caso seja retorno da cobrança original (perda antecipada da cobrança total, valor integral)
        // gera perda caso seja retorno da cobrança parcial (perda da última tentativa, valor integral)
        cliente.adicionaEvento(CobrancaNaoEfetivadaEventoDominio.builder(cliente, cobranca).build());
    }
}
