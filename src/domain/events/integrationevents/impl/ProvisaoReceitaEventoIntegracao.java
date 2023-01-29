package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.integrationevents.EventoIntegracao;

public class ProvisaoReceitaEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private ProvisaoReceitaEventoIntegracao(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static ProvisaoReceitaEventoIntegracao builder(Cliente cliente, Cobranca cobranca) {
        return new ProvisaoReceitaEventoIntegracao(cliente, cobranca);
    }

    public ProvisaoReceitaEventoIntegracao build() {
        return this;
    }
}
