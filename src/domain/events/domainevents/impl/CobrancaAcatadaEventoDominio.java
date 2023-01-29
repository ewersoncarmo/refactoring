package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;

public class CobrancaAcatadaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private CobrancaAcatadaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaAcatadaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaAcatadaEventoDominio(cliente, cobranca);
    }

    public CobrancaAcatadaEventoDominio build() {
        return this;
    }
}
