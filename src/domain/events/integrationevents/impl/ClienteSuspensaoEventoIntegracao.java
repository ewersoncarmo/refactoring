package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.events.integrationevents.EventoIntegracao;

public class ClienteSuspensaoEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;

    private ClienteSuspensaoEventoIntegracao(Cliente cliente) {
        this.cliente = cliente;
    }

    public static ClienteSuspensaoEventoIntegracao builder(Cliente cliente) {
        return new ClienteSuspensaoEventoIntegracao(cliente);
    }

    public ClienteSuspensaoEventoIntegracao build() {
        return this;
    }
}
