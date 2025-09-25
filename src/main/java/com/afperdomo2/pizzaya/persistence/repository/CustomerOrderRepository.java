package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrderEntity, Long> {
}
