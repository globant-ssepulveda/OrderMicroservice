package grandmasfood.order.application.mapper;

import grandmasfood.order.application.dto.OrderResponseDto;
import grandmasfood.order.domain.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderResponseMapper {
    /**
     * Transforms an Order object into an OrderResponseDto object
     *
     * @param order Order object
     * @return OrderResponseDto object
     */
    OrderResponseDto mapToOrderResponseDto(Order order);
}
