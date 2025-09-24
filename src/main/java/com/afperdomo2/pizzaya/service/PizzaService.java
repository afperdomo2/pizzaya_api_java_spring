package com.afperdomo2.pizzaya.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> findAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity findById(Long id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }
}
