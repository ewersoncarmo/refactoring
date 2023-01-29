package domain.gateway;

import domain.events.domainevents.EventoDominio;

public interface ClienteGateway {

    void execute(EventoDominio evento);
}
