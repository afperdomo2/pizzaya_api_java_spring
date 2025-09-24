package com.afperdomo2.pizzaya.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.service.PizzaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pizzas")
@Tag(name = "Pizzas", description = "Operaciones relacionadas con las pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las pizzas", description = "Devuelve una lista de todas las pizzas")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas obtenida exitosamente")
    public ResponseEntity<List<PizzaEntity>> findAll() {
        return ResponseEntity.ok(this.pizzaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una pizza por ID", description = "Devuelve una pizza específica")
    @ApiResponse(responseCode = "200", description = "Pizza encontrada")
    @ApiResponse(responseCode = "404", description = "Pizza no encontrada", content = @Content())
    public ResponseEntity<PizzaEntity> findById(
            @Parameter(description = "ID de la pizza", example = "1") @PathVariable Long id) {
        PizzaEntity pizza = this.pizzaService.findById(id);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pizza);
    }
}
