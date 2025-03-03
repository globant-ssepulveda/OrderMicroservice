package grandmasfood.order.domain.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private String uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuId;
    private int quantity;
    private String extraInformation;
    private float subTotal;
    private float tax;
    private float grandTotal;
    private boolean delivered;
    private LocalDateTime deliveredDate;

    public Order(){
        this.uuid = UUID.randomUUID().toString();
        this.tax = 19.0f;
    }

    public Order (String uuid, LocalDateTime creationDateTime, String clientDocument, String productUuId, int quantity, String extraInformation, float subTotal, float tax, float grandTotal, boolean delivered, LocalDateTime deliveredDate){
        this.uuid = uuid != null ? uuid : UUID.randomUUID().toString();
        this.creationDateTime = creationDateTime;
        this.clientDocument = clientDocument;
        this.productUuId = productUuId;
        this.quantity = quantity;
        this.extraInformation = extraInformation;
        this.subTotal = subTotal;
        this.tax = tax != 0 ? tax : 19.0f;
        this.grandTotal = grandTotal;
        this.delivered = delivered;
        this.deliveredDate = deliveredDate;
    }
}
