package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.entity.OrderType;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrderEntity, Long> {
    List<CustomerOrderEntity> findAllByDateAfter(LocalDateTime date);

    List<CustomerOrderEntity> findAllByOrderTypeIn(List<String> orderTypes);
}
