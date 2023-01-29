package domain.entities;

import domain.events.domainevents.EventoDominio;

import java.util.List;

public class Cliente {

    private EventoDominio eventoDominio;

    private ClienteSituacao clienteSituacao;
    private List<Cobranca> cobrancas;

    public void adicionaEvento(EventoDominio eventoDominio) {
        this.eventoDominio = eventoDominio;
    }

    public boolean isFaturado() {
        return clienteSituacao instanceof ClienteSituacaoFaturado;
    }

    public boolean isSuspenso() {
        return clienteSituacao instanceof ClienteSituacaoSuspenso;
    }

    public List<Cobranca> getCobrancas() {
        return cobrancas;
    }
}
