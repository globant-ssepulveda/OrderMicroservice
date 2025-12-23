package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Order;

import java.util.Optional;
import java.util.UUID;

public interface IOrderPersistencePort {
    Order createOrder(Order order);
    Order updateOrder(Order order);
  Optional<Order> getOrderByUuid(UUID uuid);
}
