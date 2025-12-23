package grandmasfood.order.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private UUID uuid;
    private float price;
}
