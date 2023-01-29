package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;
import domain.events.integrationevents.impl.ClienteCancelamentoEventoIntegracao;
import domain.events.integrationevents.impl.ReconhecimentoPerdaEventoIntegracao;

import java.util.Optional;

public class CobrancaRejeitadaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerdaEventoIntegracao;
    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamentoEventoIntegracao;

    private CobrancaRejeitadaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaRejeitadaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaRejeitadaEventoDominio(cliente, cobranca);
    }

    public CobrancaRejeitadaEventoDominio build() {
        reconhecimentoPerdaEventoIntegracao = reconhecimentoPerda();
        clienteCancelamentoEventoIntegracao = clienteCancelamento();
        return this;
    }

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerda() {
        return cobranca.gerouProvisao(cliente) ? Optional.of(ReconhecimentoPerdaEventoIntegracao.builder(cliente, cobranca).build()) : Optional.empty();
    }

    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamento() {
        return cliente.isFaturado() || cliente.isSuspenso() ? Optional.of(ClienteCancelamentoEventoIntegracao.builder(cliente).build()) : Optional.empty();
    }

    public Optional<ReconhecimentoPerdaEventoIntegracao> getReconhecimentoPerdaEventoIntegracao() {
        return reconhecimentoPerdaEventoIntegracao;
    }

    public Optional<ClienteCancelamentoEventoIntegracao> getClienteCancelamentoEventoIntegracao() {
        return clienteCancelamentoEventoIntegracao;
    }
}
