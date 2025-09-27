package com.afperdomo2.pizzaya.persistence.repository;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Long> {
}
