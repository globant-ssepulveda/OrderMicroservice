package grandmasfood.order.infrastructure.jpa.adapter;

import grandmasfood.order.application.dto.ProductResponseDto;
import grandmasfood.order.domain.models.Product;
import grandmasfood.order.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.rmi.server.ServerCloneException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductHttpAdapter implements IProductPersistencePort {

  private final WebClient productWebClient;

  @Override
  public Optional<Product> getProductByUuid(UUID uuid) {
    try {
      ProductResponseDto response = productWebClient.get()
          .uri("/{uuid}", uuid)
          .retrieve()
          .bodyToMono(ProductResponseDto.class)
          .block();

      return Optional.ofNullable(response)
          .map(this::toDomain);

    } catch (WebClientResponseException.NotFound e) {
      return Optional.empty();
    } catch (Exception e) {
      throw new RuntimeException("Error connecting to Product Service", e);
    }
  }

  public Optional<Product> productFallback(UUID uuid, Throwable t) throws ServerCloneException {
    throw new ServerCloneException("Product Service is unavailable");
  }

  private Product toDomain(ProductResponseDto dto) {
    Product product = new Product();
    product.setUuid(dto.getUuid());
    product.setPrice(dto.getPrice());
    return product;
  }
}
