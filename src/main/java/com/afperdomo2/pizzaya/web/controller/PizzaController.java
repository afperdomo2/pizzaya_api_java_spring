package com.afperdomo2.pizzaya.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.service.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAllPizzas() {
        return ResponseEntity.ok(this.pizzaService.getAllPizzas());
    }
}
