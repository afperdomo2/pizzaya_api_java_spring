package com.afperdomo2.pizzaya.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.afperdomo2.pizzaya.persistence.domain.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.persistence.mapper.PizzaMapper;
import com.afperdomo2.pizzaya.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;

    public PizzaService(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaMapper = pizzaMapper;
    }

    public List<PizzaEntity> findAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity findById(Long id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity create(CreatePizzaDto dto) {
        PizzaEntity pizza = pizzaMapper.toEntity(dto);
        return this.pizzaRepository.save(pizza);
    }
}
