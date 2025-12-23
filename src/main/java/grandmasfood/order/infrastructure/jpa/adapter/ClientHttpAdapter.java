package grandmasfood.order.infrastructure.jpa.adapter;

import grandmasfood.order.application.dto.ClientResponseDto;
import grandmasfood.order.domain.models.Client;
import grandmasfood.order.domain.spi.IClientPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientHttpAdapter implements IClientPersistencePort {

  private final WebClient clientWebClient;

  @Override
  public Optional<Client> getClientByDocument(String document) {
    try {
      ClientResponseDto response = clientWebClient.get()
          .uri("/{document}", document)
          .retrieve()
          .bodyToMono(ClientResponseDto.class)
          .block();

      return Optional.ofNullable(response)
          .map(this::toDomain);

    } catch (WebClientResponseException.NotFound e) {
      return Optional.empty();
    } catch (Exception e) {
      throw new RuntimeException("Error al comunicarse con el servicio de Clientes", e);
    }
  }

  private Client toDomain(ClientResponseDto dto) {
    return Client.builder()
        .document(dto.getDocument())
        .build();
  }
}