package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.integrationevents.impl.ClienteSuspensaoEventoIntegracao;
import domain.events.domainevents.EventoDominio;

import java.util.Optional;

public class CobrancaNaoGeradaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private Optional<ClienteSuspensaoEventoIntegracao> clienteSuspensoEventoIntegracao;

    private CobrancaNaoGeradaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaNaoGeradaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaNaoGeradaEventoDominio(cliente, cobranca);
    }

    public CobrancaNaoGeradaEventoDominio build() {
        clienteSuspensoEventoIntegracao = clienteSuspenso();
        return this;
    }

    private Optional<ClienteSuspensaoEventoIntegracao> clienteSuspenso() {
        return cliente.isSuspenso() ? Optional.of(ClienteSuspensaoEventoIntegracao.builder(cliente).build()) : Optional.empty();
    }

    public Optional<ClienteSuspensaoEventoIntegracao> getClienteSuspensoEventoIntegracao() {
        return clienteSuspensoEventoIntegracao;
    }
}
