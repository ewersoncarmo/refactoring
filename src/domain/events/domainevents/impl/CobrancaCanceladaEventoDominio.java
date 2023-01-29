package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;
import domain.events.integrationevents.impl.ReconhecimentoPerdaEventoIntegracao;

import java.util.Optional;

// TODO - analisar o que fazer com o cliente quando gerar um cancelamento de cobrança
public class CobrancaCanceladaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerdaEventoIntegracao;

    private CobrancaCanceladaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaCanceladaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaCanceladaEventoDominio(cliente, cobranca);
    }

    public CobrancaCanceladaEventoDominio build() {
        reconhecimentoPerdaEventoIntegracao = reconhecimentoPerda();
        return this;
    }

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerda() {
        return cobranca.gerouProvisao(cliente) ? Optional.of(ReconhecimentoPerdaEventoIntegracao.builder(cliente, cobranca).build()) : Optional.empty();
    }

    public Optional<ReconhecimentoPerdaEventoIntegracao> getReconhecimentoPerdaEventoIntegracao() {
        return reconhecimentoPerdaEventoIntegracao;
    }
}
