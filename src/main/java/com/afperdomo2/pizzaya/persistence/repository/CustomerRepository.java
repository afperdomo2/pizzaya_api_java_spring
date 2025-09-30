package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {
    @Query("SELECT c FROM CustomerEntity c WHERE c.phone = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
