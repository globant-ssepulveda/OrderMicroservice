package grandmasfood.order.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String message) {
    super(message);
  }
}
