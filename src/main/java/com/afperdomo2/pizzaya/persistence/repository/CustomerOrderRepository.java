package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrderEntity, Long> {
    List<CustomerOrderEntity> findAllByDateAfter(LocalDateTime date);

    List<CustomerOrderEntity> findAllByOrderTypeIn(List<String> orderTypes);

    @Query(value = "SELECT * FROM customer_orders WHERE customer_id = :id", nativeQuery = true)
    List<CustomerOrderEntity> findCustomerOrders(@Param("id") Long customerId);

    @Query(
            value = "SELECT co.id AS id, co.date AS date, co.total AS total, c.id AS customerId, " +
                    "c.name AS customerName, GROUP_CONCAT(p.name SEPARATOR ', ') AS pizzaNames " +
                    "FROM customer_orders co " +
                    "INNER JOIN customers c ON co.customer_id = c.id " +
                    "INNER JOIN order_items oi ON co.id = oi.customer_order_id " +
                    "INNER JOIN pizzas p ON oi.pizza_id = p.id " +
                    "WHERE co.id = :orderId " +
                    "GROUP BY co.id, co.date, co.total, c.id, c.name",
            nativeQuery = true
    )
    OrderSummary findOrderSummary(@Param("orderId") Long orderId);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("customer_id") Long customerId, @Param("order_type") String orderType);
}
