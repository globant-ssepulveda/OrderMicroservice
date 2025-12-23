package grandmasfood.order.application.mapper;

import grandmasfood.order.application.dto.OrderRequestDto;
import grandmasfood.order.application.dto.OrderUpdateRequestDto;
import grandmasfood.order.domain.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderRequestMapper {
    /**
     * Transform OrderRequestDto to Order
     *
     * @param orderRequestDto OrderRequestDto
     * @return Order
     */
    @Mapping(source = "productUuid", target = "productUuid")
    Order mapToOrderRequestDto(OrderRequestDto orderRequestDto);


    /**
     * Transform OrderUpdateRequestDto to Order
     *
     * @param orderUpdateRequestDto OrderUpdateRequestDto
     * @return Order
     */
    Order mapToOrderUpdateRequestDto(OrderUpdateRequestDto orderUpdateRequestDto);
}
