package com.afperdomo2.pizzaya.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Long> {

}
