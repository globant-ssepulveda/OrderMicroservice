package grandmasfood.order.domain.api;

import grandmasfood.order.domain.models.Order;

public interface IOrderServicePort {
    void createOrder(Order order);
    void updateOrder(Order order);
}
