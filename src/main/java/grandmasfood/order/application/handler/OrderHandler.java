package grandmasfood.order.application.handler;

import grandmasfood.order.application.dto.OrderRequestDto;
import grandmasfood.order.application.dto.OrderResponseDto;
import grandmasfood.order.application.dto.OrderUpdateRequestDto;
import grandmasfood.order.application.mapper.IOrderRequestMapper;
import grandmasfood.order.application.mapper.IOrderResponseMapper;
import grandmasfood.order.domain.api.IOrderServicePort;
import grandmasfood.order.domain.models.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {


    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;


    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderRequestMapper.mapToOrderRequestDto(orderRequestDto);
        Order createdOrder = orderServicePort.createOrder(order);
        return orderResponseMapper.mapToOrderResponseDto(createdOrder);
    }

    @Override
    public OrderResponseDto updateOrder(OrderUpdateRequestDto orderUpdateRequestDto) {
        Order order = orderRequestMapper.mapToOrderUpdateRequestDto(orderUpdateRequestDto);
        Order updateOrder = orderServicePort.updateOrder(order);
        return orderResponseMapper.mapToOrderResponseDto(updateOrder);

    }
}
