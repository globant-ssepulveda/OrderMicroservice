package grandmasfood.order.domain.models;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Order {
  private Long idOrder;
  private UUID uuid;
  private LocalDateTime creationDateTime;
  private String clientDocument;
  private UUID productUuid;
  private int quantity;
  private String extraInformation;
  private float subTotal;
  private float tax;
  private float grandTotal;
  private boolean delivered;
  private LocalDateTime deliveredDate;
}

