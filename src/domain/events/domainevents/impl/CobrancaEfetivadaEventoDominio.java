package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;
import domain.events.integrationevents.impl.ClienteCancelamentoEventoIntegracao;
import domain.events.integrationevents.impl.ReconhecimentoPerdaEventoIntegracao;
import domain.events.integrationevents.impl.ReconhecimentoReceitaEventoIntegracao;

import java.util.Optional;

public class CobrancaEfetivadaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private ReconhecimentoReceitaEventoIntegracao reconhecimentoReceitaEventoIntegracao;
    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerdaEventoIntegracao;
    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamentoEventoIntegracao;

    private CobrancaEfetivadaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaEfetivadaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaEfetivadaEventoDominio(cliente, cobranca);
    }

    public CobrancaEfetivadaEventoDominio build() {
        reconhecimentoReceitaEventoIntegracao = reconhecimentoReceita();
        reconhecimentoPerdaEventoIntegracao = reconhecimentoPerda();
        clienteCancelamentoEventoIntegracao = clienteCancelamento();
        return this;
    }

    private ReconhecimentoReceitaEventoIntegracao reconhecimentoReceita() {
        return ReconhecimentoReceitaEventoIntegracao.builder(cliente, cobranca).build();
    }

    private Optional<ReconhecimentoPerdaEventoIntegracao> reconhecimentoPerda() {
        return !cobranca.isPagamentoTotal() && cobranca.gerouProvisao(cliente) ? Optional.of(ReconhecimentoPerdaEventoIntegracao.builder(cliente, cobranca).build()) : Optional.empty();
    }

    private Optional<ClienteCancelamentoEventoIntegracao> clienteCancelamento() {
        return !cobranca.isPagamentoTotal() && cliente.isSuspenso() ? Optional.of(ClienteCancelamentoEventoIntegracao.builder(cliente).build()) : Optional.empty();
    }

    public ReconhecimentoReceitaEventoIntegracao getReconhecimentoReceitaEventoIntegracao() {
        return reconhecimentoReceitaEventoIntegracao;
    }

    public Optional<ReconhecimentoPerdaEventoIntegracao> getReconhecimentoPerdaEventoIntegracao() {
        return reconhecimentoPerdaEventoIntegracao;
    }

    public Optional<ClienteCancelamentoEventoIntegracao> getClienteCancelamentoEventoIntegracao() {
        return clienteCancelamentoEventoIntegracao;
    }
}
