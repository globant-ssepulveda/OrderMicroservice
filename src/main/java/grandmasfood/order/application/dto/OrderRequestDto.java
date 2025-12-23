package grandmasfood.order.application.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderRequestDto {
    private UUID productUuid;
    private String clientDocument;
    private int quantity;
    private String extraInformation;
}
