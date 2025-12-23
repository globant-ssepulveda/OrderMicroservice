package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Product;

import java.util.Optional;
import java.util.UUID;

public interface IProductPersistencePort {
    Optional<Product> getProductByUuid(UUID uuid);
}
