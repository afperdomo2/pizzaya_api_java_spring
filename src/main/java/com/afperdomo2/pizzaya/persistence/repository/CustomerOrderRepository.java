package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrderEntity, Long> {
    List<CustomerOrderEntity> findAllByDateAfter(LocalDateTime date);

    List<CustomerOrderEntity> findAllByOrderTypeIn(List<String> orderTypes);

    @Query(value = "SELECT * FROM customer_orders WHERE customer_id = :id", nativeQuery = true)
    List<CustomerOrderEntity> findCustomerOrders(@Param("id") Long customerId);
}
