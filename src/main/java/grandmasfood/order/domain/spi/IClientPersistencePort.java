package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Client;

public interface IClientPersistencePort {
    Client getClientByDocument(String document);
}
