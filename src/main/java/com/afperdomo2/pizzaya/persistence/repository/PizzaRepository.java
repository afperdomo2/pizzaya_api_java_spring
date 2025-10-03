package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {
    List<PizzaEntity> findAllByIsAvailableTrueOrderByPrice();

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByIsAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByIsAvailableTrueAndPriceLessThanOrderByPriceAsc(double price);

    Optional<PizzaEntity> findFirstByIsAvailableTrueAndNameIgnoreCase(String name);

    int countByIsVeganTrue();

    @Query(
            value = "UPDATE pizzas SET price = :#{#data.newPrice} WHERE id = :#{#data.id}",
            nativeQuery = true
    )
    @Modifying
    void updatePrice(@Param("data") UpdatePizzaPriceDto data);
}
