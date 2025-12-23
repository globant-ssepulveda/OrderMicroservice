package grandmasfood.order.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDto {
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
