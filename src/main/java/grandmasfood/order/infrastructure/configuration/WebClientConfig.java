package grandmasfood.order.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${micros.product.url}")
  private String productUrl;

  @Value("${micros.client.url}")
  private String clientUrl;

  @Bean
  public WebClient productWebClient(WebClient.Builder builder) {
    return builder.baseUrl(productUrl).build();
  }

  @Bean
  public WebClient clientWebClient(WebClient.Builder builder) {
    return builder.baseUrl(clientUrl).build();
  }
}
