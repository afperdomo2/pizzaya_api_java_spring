package com.afperdomo2.pizzaya.web.controller;

import com.afperdomo2.pizzaya.persistence.domain.dto.CreatePizzaDto;
import com.afperdomo2.pizzaya.persistence.domain.dto.UpdatePizzaDto;
import com.afperdomo2.pizzaya.persistence.entity.PizzaEntity;
import com.afperdomo2.pizzaya.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/cheaper-than/{price}")
    @Operation(summary = "Obtener las 3 pizzas más baratas por debajo de un precio dado", description = "Devuelve una lista de las 3 pizzas más baratas que son más baratas que el precio proporcionado")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas obtenida exitosamente")
    @ApiResponse(responseCode = "400", description = "Parámetro de precio faltante o inválido", content = @Content())
    public ResponseEntity<List<PizzaEntity>> findTop3CheaperThan(@PathVariable @Parameter(description = "Precio máximo para filtrar las pizzas", example = "15.0") double price) {
        return ResponseEntity.ok(this.pizzaService.findTop3CheaperThan(price));
    }

    @GetMapping("/vegan/count")
    @Operation(summary = "Contar pizzas veganas", description = "Devuelve el número total de pizzas veganas")
    @ApiResponse(responseCode = "200", description = "Número de pizzas veganas obtenido exitosamente")
    public int countVeganPizzas() {
        return this.pizzaService.countVeganPizzas();
    }

    @GetMapping("/available")
    @Operation(summary = "Obtener todas las pizzas disponibles", description = "Devuelve una lista de todas las pizzas disponibles ordenadas por precio")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas disponibles obtenida exitosamente")
    public ResponseEntity<List<PizzaEntity>> findAllAvailable() {
        return ResponseEntity.ok(this.pizzaService.findAllAvailable());
    }

    @GetMapping("/available/search")
    @Operation(summary = "Buscar una pizza disponible por nombre", description = "Devuelve una pizza disponible que coincide exactamente con el nombre proporcionado")
    @ApiResponse(responseCode = "200", description = "Pizza encontrada")
    @ApiResponse(responseCode = "400", description = "Parámetro de nombre faltante o inválido", content = @Content())
    @ApiResponse(responseCode = "404", description = "No se encontraron pizzas con el nombre proporcionado", content = @Content())
    public ResponseEntity<PizzaEntity> findAllAvailableByName(@RequestParam String name) {
        PizzaEntity pizza = this.pizzaService.findAvailableByName(name);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pizza);
    }

    @GetMapping("/available/with-ingredient/{ingredient}")
    @Operation(summary = "Buscar pizzas disponibles por ingrediente", description = "Devuelve una lista de pizzas disponibles que contienen el ingrediente proporcionado en su descripción")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas encontradas")
    @ApiResponse(responseCode = "400", description = "Parámetro de ingrediente faltante o inválido", content = @Content())
    public ResponseEntity<List<PizzaEntity>> findAllAvailableByIngredient(@Parameter(description = "Ingrediente a buscar en la descripción de la pizza", example = "queso") @PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.findAvailableWithIngredient(ingredient));
    }

    @GetMapping("/available/without-ingredient/{ingredient}")
    @Operation(summary = "Buscar pizzas disponibles sin un ingrediente específico", description = "Devuelve una lista de pizzas disponibles que no contienen el ingrediente proporcionado en su descripción")
    @ApiResponse(responseCode = "200", description = "Lista de pizzas encontradas")
    @ApiResponse(responseCode = "400", description = "Parámetro de ingrediente faltante o inválido", content = @Content())
    public ResponseEntity<List<PizzaEntity>> findAllAvailableWithoutIngredient(@Parameter(description = "Ingrediente a excluir de la descripción de la pizza", example = "piña") @PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaService.findAvailableWithoutIngredient(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una pizza por ID", description = "Devuelve una pizza específica")
    @ApiResponse(responseCode = "200", description = "Pizza encontrada")
    @ApiResponse(responseCode = "404", description = "Pizza no encontrada", content = @Content())
    public ResponseEntity<PizzaEntity> findById(@Parameter(description = "ID de la pizza", example = "1") @PathVariable Long id) {
        PizzaEntity pizza = this.pizzaService.findById(id);
        if (pizza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva pizza", description = "Crea una nueva pizza")
    @ApiResponse(responseCode = "201", description = "Pizza creada exitosamente")
    public ResponseEntity<PizzaEntity> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la pizza a crear", required = true) @Valid @RequestBody CreatePizzaDto pizzaDto) {
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
    public ResponseEntity<PizzaEntity> update(@Parameter(description = "ID de la pizza", example = "1") @PathVariable Long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la pizza a actualizar", required = true) @Valid @RequestBody UpdatePizzaDto pizzaDto) {
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una pizza", description = "Elimina una pizza existente")
    @ApiResponse(responseCode = "204", description = "Pizza eliminada exitosamente")
    @ApiResponse(responseCode = "500", description = "Error al eliminar la pizza", content = @Content())
    public HttpEntity<Void> delete(@Parameter(description = "ID de la pizza", example = "1") @PathVariable Long id) {
        try {
            PizzaEntity deletedPizza = this.pizzaService.delete(id);
            if (deletedPizza == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar la pizza", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
