package grandmasfood.order.domain.usecase;

import grandmasfood.order.domain.api.IOrderServicePort;
import grandmasfood.order.domain.exceptions.ClientNotFoundException;
import grandmasfood.order.domain.exceptions.ProductNotFoundException;
import grandmasfood.order.domain.models.Client;
import grandmasfood.order.domain.models.Order;
import grandmasfood.order.domain.models.Product;
import grandmasfood.order.domain.spi.IClientPersistencePort;
import grandmasfood.order.domain.spi.IOrderPersistencePort;
import grandmasfood.order.domain.spi.IProductPersistencePort;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IProductPersistencePort productPersistencePort;
    private final IClientPersistencePort clientPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IProductPersistencePort productPersistencePort, IClientPersistencePort clientPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.productPersistencePort = productPersistencePort;
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        Product product = productPersistencePort.getProductByUuid(order.getProductUuId());
        Client client = clientPersistencePort.getClientByDocument(order.getClientDocument());

        if(product == null){
            throw new ProductNotFoundException("Product not found");
        }
        if(client == null){
            throw new ClientNotFoundException("Client not found");
        }

        order.setClientDocument(client.getDocument());
        order.setProductUuId(product.getUuid());
        order.setCreationDateTime(LocalDateTime.now());
        order.setSubTotal(product.getPrice());
        order.setGrandTotal(order.getSubTotal() + (order.getSubTotal() * order.getTax()));
        order.setDelivered(false);
        order.setDeliveredDate(null);
        orderPersistencePort.createOrder(order);

    }

    @Override
    public void updateOrder(Order order) {

    }
}
