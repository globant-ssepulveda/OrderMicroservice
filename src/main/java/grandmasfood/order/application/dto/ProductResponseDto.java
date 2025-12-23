package grandmasfood.order.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class ProductResponseDto {
    private UUID uuid;
    private String fantasyName;
    private float price;
    private boolean available;
}
