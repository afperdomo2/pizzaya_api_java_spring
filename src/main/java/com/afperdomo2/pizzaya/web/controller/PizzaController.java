package com.afperdomo2.pizzaya.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.afperdomo2.pizzaya.persistence.domain.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.persistence.domain.dto.UpdatePizzaDto;
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
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);
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
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva pizza", description = "Crea una nueva pizza")
    @ApiResponse(responseCode = "201", description = "Pizza creada exitosamente")
    public ResponseEntity<PizzaEntity> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la pizza a crear", required = true) @Valid @RequestBody CreatePizzaDto pizzaDto) {
        try {
            PizzaEntity createdPizza = this.pizzaService.create(pizzaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPizza);
        } catch (Exception e) {
            log.error("Error al crear la pizza", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una pizza existente", description = "Actualiza los datos de una pizza existente")
    @ApiResponse(responseCode = "200", description = "Pizza actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Pizza no encontrada", content = @Content())
    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content())
    public ResponseEntity<PizzaEntity> update(
            @Parameter(description = "ID de la pizza", example = "1") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la pizza a actualizar", required = true) @Valid @RequestBody UpdatePizzaDto pizzaDto) {
        try {
            PizzaEntity updatedPizza = this.pizzaService.update(id, pizzaDto);
            if (updatedPizza == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedPizza);
        } catch (Exception e) {
            log.error("Error al actualizar la pizza", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
