package grandmasfood.order.application.handler;

import grandmasfood.order.application.dto.OrderRequestDto;
import grandmasfood.order.application.dto.OrderResponseDto;
import grandmasfood.order.application.dto.OrderUpdateRequestDto;

public interface IOrderHandler {
  OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
  OrderResponseDto updateOrder(OrderUpdateRequestDto orderUpdateRequestDto);
}