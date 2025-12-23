package grandmasfood.order.infrastructure.jpa.repository;

import grandmasfood.order.infrastructure.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdOrder(Long idOrder);
    Optional<OrderEntity> findByUuid(UUID uuid);
}
