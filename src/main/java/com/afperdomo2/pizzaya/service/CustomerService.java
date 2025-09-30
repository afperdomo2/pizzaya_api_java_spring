package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.CustomerEntity;
import com.afperdomo2.pizzaya.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone) {
        return this.customerRepository.findByPhone(phone);
    }
}
