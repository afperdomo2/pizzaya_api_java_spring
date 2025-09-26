package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.entity.OrderType;
import com.afperdomo2.pizzaya.persistence.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    public List<CustomerOrderEntity> findAll() {
        return this.customerOrderRepository.findAll();
    }

    public List<CustomerOrderEntity> findAllToday() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.customerOrderRepository.findAllByDateAfter(today);
    }

    public List<CustomerOrderEntity> findAllOutsideOrders() {
        List<String> outsideOrderTypeCodes = List.of(OrderType.DELIVERY.getCode(), OrderType.CARRYOUT.getCode());
        return this.customerOrderRepository.findAllByOrderTypeIn(outsideOrderTypeCodes);
    }
}
