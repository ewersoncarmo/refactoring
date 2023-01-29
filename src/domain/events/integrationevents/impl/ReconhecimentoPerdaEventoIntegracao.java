package domain.events.integrationevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.integrationevents.EventoIntegracao;

public class ReconhecimentoPerdaEventoIntegracao implements EventoIntegracao {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private Double valorPerda;

    private ReconhecimentoPerdaEventoIntegracao(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static ReconhecimentoPerdaEventoIntegracao builder(Cliente cliente, Cobranca cobranca) {
        return new ReconhecimentoPerdaEventoIntegracao(cliente, cobranca);
    }

    public ReconhecimentoPerdaEventoIntegracao build() {
        this.valorPerda = cobranca.getValorPerda();
        return this;
    }
}
