package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.CustomerOrderEntity;
import com.afperdomo2.pizzaya.persistence.entity.OrderType;
import com.afperdomo2.pizzaya.persistence.projection.OrderSummary;
import com.afperdomo2.pizzaya.persistence.repository.CustomerOrderRepository;
import com.afperdomo2.pizzaya.service.dto.RandomOrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<CustomerOrderEntity> findCustomerOrders(Long customerId) {
        return this.customerOrderRepository.findCustomerOrders(customerId);
    }

    public OrderSummary findOrderSummary(Long orderId) {
        return this.customerOrderRepository.findOrderSummary(orderId);
    }

    @Transactional
    public Boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        System.out.println("Saving random order for customer ID: " + randomOrderDto.getCustomerId() +
                " with order type: " + randomOrderDto.getOrderType());
        return this.customerOrderRepository.saveRandomOrder(
                randomOrderDto.getCustomerId(),
                randomOrderDto.getOrderType()
        );
    }
}
