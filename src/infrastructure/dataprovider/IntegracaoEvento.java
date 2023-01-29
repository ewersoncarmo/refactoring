package infrastructure.dataprovider;

import domain.events.integrationevents.EventoIntegracao;

public interface IntegracaoEvento<T extends EventoIntegracao> {

    void execute(T evento);
}
