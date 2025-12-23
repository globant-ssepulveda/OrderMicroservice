package grandmasfood.order.infrastructure.rest;

import grandmasfood.order.application.dto.OrderRequestDto;
import grandmasfood.order.application.dto.OrderResponseDto;
import grandmasfood.order.application.dto.OrderUpdateRequestDto;
import grandmasfood.order.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "Create new order", description = "Creates a new order with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Client or product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Validated @RequestBody OrderRequestDto orderRequestDto) {
        try {
            OrderResponseDto orderResponseDto = orderHandler.createOrder(orderRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(
            summary = "Update an existing order (delivered status/date)",
            description = "Updates the 'delivered' field and optionally the 'deliveredDate' field of the specified order."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable("uuid") UUID uuid, @PathVariable("timestamp") LocalDateTime deliveredDate) {
        OrderUpdateRequestDto orderUpdateRequestDto = new OrderUpdateRequestDto();
        orderUpdateRequestDto.setUuid(uuid);
        orderUpdateRequestDto.setDeliveredDate(deliveredDate);
        orderUpdateRequestDto.setDelivered(true);
        OrderResponseDto updatedOrder = orderHandler.updateOrder(orderUpdateRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }
}
