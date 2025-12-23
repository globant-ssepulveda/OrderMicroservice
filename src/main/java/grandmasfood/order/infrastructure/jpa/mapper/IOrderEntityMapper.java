package grandmasfood.order.infrastructure.jpa.mapper;

import grandmasfood.order.domain.models.Order;
import grandmasfood.order.infrastructure.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderEntityMapper {

    /**
     * Transform Order to OrderEntity
     *
     * @param order Order
     * @return OrderEntity
     */
    OrderEntity toOrderEntity(Order order);

    /**
     * Transform OrderEntity to Order
     *
     * @param entity OrderEntity
     * @return Order
     */
    Order toOrder(OrderEntity entity);

}