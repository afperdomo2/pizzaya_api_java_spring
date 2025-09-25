package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.repository.CustomerOrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Async
    public List<CustomerOrderEntity> findAll() {
        return this.customerOrderRepository.findAll();
    }
}
