package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Client;

import java.util.Optional;

public interface IClientPersistencePort {
    Optional<Client> getClientByDocument(String document);
}
