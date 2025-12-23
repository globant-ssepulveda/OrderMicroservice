package grandmasfood.order.domain.api;

import grandmasfood.order.domain.models.Order;

public interface IOrderServicePort {
  Order createOrder(Order order);
  Order updateOrder(Order order);
}
