package domain.events.domainevents.impl;

import domain.entities.Cliente;
import domain.entities.Cobranca;
import domain.events.domainevents.EventoDominio;
import domain.events.integrationevents.impl.ProvisaoReceitaEventoIntegracao;
import domain.events.integrationevents.impl.SolicitacaoDebitoEventoIntegracao;

public class CobrancaGeradaEventoDominio implements EventoDominio {

    private final Cliente cliente;
    private final Cobranca cobranca;

    private SolicitacaoDebitoEventoIntegracao solicitacaoDebitoEventoIntegracao;
    private ProvisaoReceitaEventoIntegracao provisaoReceitaEventoIntegracao;

    private CobrancaGeradaEventoDominio(Cliente cliente, Cobranca cobranca) {
        this.cliente = cliente;
        this.cobranca = cobranca;
    }

    public static CobrancaGeradaEventoDominio builder(Cliente cliente, Cobranca cobranca) {
        return new CobrancaGeradaEventoDominio(cliente, cobranca);
    }

    public CobrancaGeradaEventoDominio build() {
        solicitacaoDebitoEventoIntegracao = solicitacaoDebito();
        provisaoReceitaEventoIntegracao = provisaoReceita();
        return this;
    }

    private SolicitacaoDebitoEventoIntegracao solicitacaoDebito() {
        return SolicitacaoDebitoEventoIntegracao.builder(cliente, cobranca).build();
    }

    private ProvisaoReceitaEventoIntegracao provisaoReceita() {
        return ProvisaoReceitaEventoIntegracao.builder(cliente, cobranca).build();
    }

    public SolicitacaoDebitoEventoIntegracao getSolicitacaoDebitoEventoIntegracao() {
        return solicitacaoDebitoEventoIntegracao;
    }

    public ProvisaoReceitaEventoIntegracao getProvisaoReceitaEventoIntegracao() {
        return provisaoReceitaEventoIntegracao;
    }
}