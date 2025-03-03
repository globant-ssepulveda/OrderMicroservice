package grandmasfood.order.domain.spi;

import grandmasfood.order.domain.models.Order;

public interface IOrderPersistencePort {
    void createOrder(Order order);
    void updateOrder(Order order);
}
