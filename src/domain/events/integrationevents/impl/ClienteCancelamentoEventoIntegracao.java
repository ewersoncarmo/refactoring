package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.events.integrationevents.EventoIntegracao;

public class ClienteCancelamentoEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;

    private ClienteCancelamentoEventoIntegracao(Cliente cliente) {
        this.cliente = cliente;
    }

    public static ClienteCancelamentoEventoIntegracao builder(Cliente cliente) {
        return new ClienteCancelamentoEventoIntegracao(cliente);
    }

    public ClienteCancelamentoEventoIntegracao build() {
        return this;
    }
}
