package infrastructure.dataprovider;

import domain.events.domainevents.EventoDominio;
import domain.events.domainevents.impl.*;
import domain.events.integrationevents.impl.ClienteCancelamentoEventoIntegracao;
import domain.events.integrationevents.impl.ClienteSuspensaoEventoIntegracao;
import domain.events.integrationevents.impl.ReconhecimentoPerdaEventoIntegracao;
import domain.events.integrationevents.impl.SolicitacaoDebitoEventoIntegracao;
import domain.gateway.ClienteGateway;

public class ClienteDataProviderImpl implements ClienteGateway {

    private SolicitacaoDebitoImpl solicitacaoDebitoImpl;
    private ProvisaoReceitaImpl provisaoReceitaImpl;
    private ReconhecimentoReceitaImpl reconhecimentoReceitaImpl;
    private ReconhecimentoPerdaImpl reconhecimentoPerdaImpl;
    private ClienteSuspensaoImpl clienteSuspensaoImpl;
    private ClienteCancelamentoImpl clienteCancelamentoImpl;

    @Override
    public void execute(EventoDominio evento) {
        this.solicitacaoDebitoImpl = new SolicitacaoDebitoImpl();
        this.provisaoReceitaImpl = new ProvisaoReceitaImpl();
        this.reconhecimentoReceitaImpl = new ReconhecimentoReceitaImpl();
        this.reconhecimentoPerdaImpl = new ReconhecimentoPerdaImpl();
        this.clienteSuspensaoImpl = new ClienteSuspensaoImpl();
        this.clienteCancelamentoImpl = new ClienteCancelamentoImpl();
    }

    public void executaCobrancaGeradaEventoDominio(CobrancaGeradaEventoDominio eventoDominio) {
        executaSolicitacaoDebito(eventoDominio.getSolicitacaoDebitoEventoIntegracao());
        provisaoReceitaImpl.execute(eventoDominio.getProvisaoReceitaEventoIntegracao());
    }

    public void executaCobrancaNaoGeradaEventoDominio(CobrancaNaoGeradaEventoDominio eventoDominio) {
        eventoDominio.getClienteSuspensoEventoIntegracao().ifPresent(evento -> {
            clienteSuspensaoImpl.execute(evento);
        });
    }

    public void executaCobrancaAcatadaEventoDominio(CobrancaAcatadaEventoDominio eventoDominio) {}

    public void executaCobrancaRejeitadaEventoDominio(CobrancaRejeitadaEventoDominio eventoDominio) {
        eventoDominio.getReconhecimentoPerdaEventoIntegracao().ifPresent(this::executaReconhecimentoPerda);
        eventoDominio.getClienteCancelamentoEventoIntegracao().ifPresent(this::executaClienteCancelamento);
    }

    public void executaCobrancaCanceladaEventoDominio(CobrancaCanceladaEventoDominio eventoDominio) {
        eventoDominio.getReconhecimentoPerdaEventoIntegracao().ifPresent(this::executaReconhecimentoPerda);
    }

    public void executaCobrancaEfetivadaEventoDominio(CobrancaEfetivadaEventoDominio eventoDominio) {
        reconhecimentoReceitaImpl.execute(eventoDominio.getReconhecimentoReceitaEventoIntegracao());
        eventoDominio.getReconhecimentoPerdaEventoIntegracao().ifPresent(this::executaReconhecimentoPerda);
        eventoDominio.getClienteCancelamentoEventoIntegracao().ifPresent(this::executaClienteCancelamento);
    }

    public void executaCobrancaNaoEfetivadaEventoDominio(CobrancaNaoEfetivadaEventoDominio eventoDominio) {
        eventoDominio.getSolicitacaoDebitoEventoIntegracao().ifPresent(this::executaSolicitacaoDebito);
        eventoDominio.getReconhecimentoPerdaEventoIntegracao().ifPresent(this::executaReconhecimentoPerda);
        eventoDominio.getClienteCancelamentoEventoIntegracao().ifPresent(this::executaClienteCancelamento);
    }

    private void executaSolicitacaoDebito(SolicitacaoDebitoEventoIntegracao solicitacaoDebitoEventoIntegracao) {
        solicitacaoDebitoImpl.execute(solicitacaoDebitoEventoIntegracao);
    }

    private void executaReconhecimentoPerda(ReconhecimentoPerdaEventoIntegracao reconhecimentoPerdaEventoIntegracao) {
        reconhecimentoPerdaImpl.execute(reconhecimentoPerdaEventoIntegracao);
    }

    private void executaClienteCancelamento(ClienteCancelamentoEventoIntegracao clienteCancelamentoEventoIntegracao) {
        clienteCancelamentoImpl.execute(clienteCancelamentoEventoIntegracao);
    }
}


