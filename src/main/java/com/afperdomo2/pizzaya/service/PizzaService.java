package com.afperdomo2.pizzaya.service;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.persistence.mapper.PizzaMapper;
import com.afperdomo2.pizzaya.persistence.repository.PizzaPagSortRepository;
import com.afperdomo2.pizzaya.persistence.repository.PizzaRepository;
import com.afperdomo2.pizzaya.service.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.service.dto.UpdatePizzaDto;
import com.afperdomo2.pizzaya.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;
    private final PizzaMapper pizzaMapper;

    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
        this.pizzaMapper = pizzaMapper;
    }

    public int countVeganPizzas() {
        return this.pizzaRepository.countByIsVeganTrue();
    }

    public Page<PizzaEntity> findALl(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.pizzaPagSortRepository.findAll(pageable);
    }

    public Page<PizzaEntity> findAllAvailable(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return this.pizzaPagSortRepository.findAllByIsAvailableTrue(pageable);
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

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
        this.pizzaRepository.updatePrice(updatePizzaPriceDto);
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
