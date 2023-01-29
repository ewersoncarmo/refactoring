package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;
import domain.events.integrationevents.impl.ClienteCancelamentoEventoIntegracao;
import domain.events.integrationevents.impl.ReconhecimentoPerdaEventoIntegracao;
import domain.events.integrationevents.impl.SolicitacaoDebitoEventoIntegracao;

import java.util.Optional;

public class CobrancaNaoEfetivadaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private Optional<SolicitacaoDebitoEventoIntegracao> solicitacaoDebitoEventoIntegracao;
    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerdaEventoIntegracao;
    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamentoEventoIntegracao;

    private CobrancaNaoEfetivadaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaNaoEfetivadaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaNaoEfetivadaEventoDominio(cliente, cobranca);
    }

    public CobrancaNaoEfetivadaEventoDominio build() {
        Optional<Cobranca> cobrancaParcial = cobranca.retornaCobrancaParcial();

        solicitacaoDebitoEventoIntegracao = solicitacaoDebito(cobrancaParcial);
        reconhecimentoPerdaEventoIntegracao = reconhecimentoPerda(cobrancaParcial);
        clienteCancelamentoEventoIntegracao = clienteCancelamento(cobrancaParcial);
        return this;
    }

    private Optional<SolicitacaoDebitoEventoIntegracao> solicitacaoDebito(Optional<Cobranca> cobrancaParcial) {
        return cobrancaParcial.isPresent() ? Optional.of(SolicitacaoDebitoEventoIntegracao.builder(cliente, cobrancaParcial.get()).build()) : Optional.empty();
    }

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerda(Optional<Cobranca> cobrancaParcial) {
        return !cobrancaParcial.isPresent() && cobranca.gerouProvisao(cliente) ? Optional.of(ReconhecimentoPerdaEventoIntegracao.builder(cliente, cobranca).build()) : Optional.empty();
    }

    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamento(Optional<Cobranca> cobrancaParcial) {
        return !cobrancaParcial.isPresent() && (cliente.isFaturado() || cliente.isSuspenso()) ? Optional.of(ClienteCancelamentoEventoIntegracao.builder(cliente).build()) : Optional.empty();
    }

    public Optional<SolicitacaoDebitoEventoIntegracao> getSolicitacaoDebitoEventoIntegracao() {
        return solicitacaoDebitoEventoIntegracao;
    }

    public Optional<ReconhecimentoPerdaEventoIntegracao> getReconhecimentoPerdaEventoIntegracao() {
        return reconhecimentoPerdaEventoIntegracao;
    }

    public Optional<ClienteCancelamentoEventoIntegracao> getClienteCancelamentoEventoIntegracao() {
        return clienteCancelamentoEventoIntegracao;
    }
}
