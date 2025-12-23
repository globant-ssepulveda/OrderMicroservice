package grandmasfood.order.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @JoinColumn(name = "id_client", nullable = false)
    private String clientDocument;

    @JoinColumn(name = "id_product", nullable = false)
    private UUID productUuid;

    private int quantity;

    @Column(name = "extra_information", nullable = false, length = 511)
    private String extraInformation;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    private BigDecimal tax;

    @Column(name = "grand_total", nullable = false)
    private BigDecimal grandTotal;

    @Column(name = "creation_date_time", nullable = false)
    private LocalDateTime creationDateTime;

    private boolean delivered;

    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;

}
