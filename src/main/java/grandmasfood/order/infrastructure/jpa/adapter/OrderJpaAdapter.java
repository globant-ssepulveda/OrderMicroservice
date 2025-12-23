package grandmasfood.order.infrastructure.jpa.adapter;

import grandmasfood.order.infrastructure.jpa.mapper.IOrderEntityMapper;
import lombok.RequiredArgsConstructor;
import grandmasfood.order.domain.models.Order;
import grandmasfood.order.domain.spi.IOrderPersistencePort;
import grandmasfood.order.infrastructure.jpa.entity.OrderEntity;
import grandmasfood.order.infrastructure.jpa.repository.IOrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

  private final IOrderRepository orderRepository;
  private final IOrderEntityMapper orderEntityMapper;

  @Override
  public Order createOrder(Order order) {
    OrderEntity orderEntity = orderEntityMapper.toOrderEntity(order);

    OrderEntity savedOrderEntity = orderRepository.save(orderEntity);

    return orderEntityMapper.toOrder(savedOrderEntity);
  }

  @Override
  public Order updateOrder(Order order) {
    OrderEntity existingEntity = orderRepository.findByUuid(order.getUuid())
        .orElseThrow(() -> new RuntimeException("Order not found"));

    existingEntity.setDeliveredDate(order.getDeliveredDate());
    existingEntity.setDelivered(order.isDelivered());

    OrderEntity saved = orderRepository.save(existingEntity);
    return orderEntityMapper.toOrder(saved);
  }

  @Override
  public Optional<Order> getOrderByUuid(UUID uuid) {
    return orderRepository.findByUuid(uuid)
        .map(orderEntityMapper::toOrder);
  }
}