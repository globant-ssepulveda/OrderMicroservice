package grandmasfood.order.domain.usecase;

import grandmasfood.order.domain.api.IOrderServicePort;
import grandmasfood.order.domain.exceptions.ClientNotFoundException;
import grandmasfood.order.domain.exceptions.InformationLengthException;
import grandmasfood.order.domain.exceptions.OrderNotFoundException;
import grandmasfood.order.domain.exceptions.ProductNotFoundException;
import grandmasfood.order.domain.models.Client;
import grandmasfood.order.domain.models.Order;
import grandmasfood.order.domain.models.Product;
import grandmasfood.order.domain.spi.IClientPersistencePort;
import grandmasfood.order.domain.spi.IOrderPersistencePort;
import grandmasfood.order.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static grandmasfood.order.utils.Constants.MAX_LENGTH_INFORMATION;
import static grandmasfood.order.utils.Constants.ORDER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IProductPersistencePort productPersistencePort;
    private final IClientPersistencePort clientPersistencePort;


  @Override
  public Order createOrder(Order order) {
    Product product = productPersistencePort.getProductByUuid(order.getProductUuid())
        .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    Client client = clientPersistencePort.getClientByDocument(order.getClientDocument())
        .orElseThrow(() -> new ClientNotFoundException("Client not found"));

    if (order.getExtraInformation().length() > MAX_LENGTH_INFORMATION) {
      throw new InformationLengthException("The description is too long");
    }
    order.setUuid(UUID.randomUUID());
    order.setClientDocument(client.getDocument());
    order.setProductUuid(product.getUuid());
    order.setCreationDateTime(LocalDateTime.now());
    order.setSubTotal(product.getPrice() * order.getQuantity());
    order.setTax(0.19f);
    order.setGrandTotal(order.getSubTotal() + (order.getSubTotal() * order.getTax()));
    order.setDelivered(false);
    order.setDeliveredDate(null);
    return orderPersistencePort.createOrder(order);
  }

  @Override
  public Order updateOrder(Order orderUpdateInfo) {
    Order existingOrder = orderPersistencePort.getOrderByUuid(orderUpdateInfo.getUuid())
        .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND));
    existingOrder.setDelivered(orderUpdateInfo.isDelivered());
    existingOrder.setDeliveredDate(orderUpdateInfo.getDeliveredDate());
    return orderPersistencePort.updateOrder(existingOrder);
  }
}
