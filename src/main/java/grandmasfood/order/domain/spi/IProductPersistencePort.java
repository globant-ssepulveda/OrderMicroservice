package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Product;

public interface IProductPersistencePort {
    Product getProductByUuid(String uuid);
}
