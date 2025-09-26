package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.domain.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.persistence.domain.dto.UpdatePizzaDto;
import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.persistence.mapper.PizzaMapper;
import com.afperdomo2.pizzaya.persistence.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;

    public PizzaService(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaMapper = pizzaMapper;
    }

    public int countVeganPizzas() {
        return this.pizzaRepository.countByIsVeganTrue();
    }

    public List<PizzaEntity> findAll() {
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> findAllAvailable() {
        return this.pizzaRepository.findAllByIsAvailableTrueOrderByPrice();
    }

    public PizzaEntity findAvailableByName(String name) {
        return this.pizzaRepository.findFirstByIsAvailableTrueAndNameIgnoreCase(name).orElse(null);
    }

    public List<PizzaEntity> findAvailableWithIngredient(String description) {
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> findAvailableWithoutIngredient(String description) {
        return this.pizzaRepository.findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public List<PizzaEntity> findTop3CheaperThan(double price) {
        return this.pizzaRepository.findTop3ByIsAvailableTrueAndPriceLessThanOrderByPriceAsc(price);
    }

    public PizzaEntity findById(Long id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity create(CreatePizzaDto dto) {
        PizzaEntity pizza = pizzaMapper.toEntity(dto);
        return this.pizzaRepository.save(pizza);
    }

    public PizzaEntity update(Long id, UpdatePizzaDto dto) {
        PizzaEntity pizza = this.pizzaRepository.findById(id).orElse(null);
        if (pizza == null) {
            return null;
        }
        pizzaMapper.updateEntity(pizza, dto);
        return this.pizzaRepository.save(pizza);
    }

    public PizzaEntity delete(Long id) {
        PizzaEntity pizza = this.pizzaRepository.findById(id).orElse(null);
        if (pizza == null) {
            return null;
        }
        this.pizzaRepository.deleteById(id);
        return pizza;
    }
}
