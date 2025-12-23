package grandmasfood.order.infrastructure.exceptions;

public class ServiceUnavailableException extends RuntimeException {
  public ServiceUnavailableException(String message) {
    super(message);
  }
}
