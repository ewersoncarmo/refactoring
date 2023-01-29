package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.integrationevents.EventoIntegracao;

public class ReconhecimentoReceitaEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private ReconhecimentoReceitaEventoIntegracao(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static ReconhecimentoReceitaEventoIntegracao builder(Cliente cliente, Cobranca cobranca) {
        return new ReconhecimentoReceitaEventoIntegracao(cliente, cobranca);
    }

    public ReconhecimentoReceitaEventoIntegracao build() {
        return this;
    }
}
