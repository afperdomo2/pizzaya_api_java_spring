package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    List<PizzaEntity> findAllByIsAvailableTrueOrderByPrice();

    PizzaEntity findByIsAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    int countByIsVeganTrue();
}
