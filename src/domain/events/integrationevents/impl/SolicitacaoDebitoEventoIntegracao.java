package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.integrationevents.EventoIntegracao;

public class SolicitacaoDebitoEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private SolicitacaoDebitoEventoIntegracao(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static SolicitacaoDebitoEventoIntegracao builder(Cliente cliente, Cobranca cobranca) {
        return new SolicitacaoDebitoEventoIntegracao(cliente, cobranca);
    }

    public SolicitacaoDebitoEventoIntegracao build() {
        return this;
    }
}
