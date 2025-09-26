package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    List<PizzaEntity> findAllByIsAvailableTrueOrderByPrice();

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByIsAvailableTrueAndPriceLessThanOrderByPriceAsc(double price);

    Optional<PizzaEntity> findFirstByIsAvailableTrueAndNameIgnoreCase(String name);

    int countByIsVeganTrue();
}
