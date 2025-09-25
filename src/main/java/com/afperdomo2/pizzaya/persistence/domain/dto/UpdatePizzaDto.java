package com.afperdomo2.pizzaya.persistence.domain.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO para actualizar una pizza")
public record UpdatePizzaDto(
        @Schema(description = "Nombre de la pizza", example = "Margherita")
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,

        @Schema(description = "Descripción de la pizza", example = "Pizza clásica con tomate, mozzarella y albahaca")
        @NotBlank(message = "La descripción no puede estar vacía")
        String description,

        @Schema(description = "Precio de la pizza", example = "12.99")
        @Positive(message = "El precio debe ser un valor positivo")
        @Digits(integer = 10, fraction = 2, message = "El precio debe tener hasta 10 dígitos enteros y 2 decimales")
        BigDecimal price,

        @Schema(description = "Indica si la pizza es vegetariana", example = "false")
        Boolean isVegetarian,

        @Schema(description = "Indica si la pizza es vegana", example = "false")
        Boolean isVegan,

        @Schema(description = "Indica si la pizza está disponible", example = "true")
        Boolean isAvailable) {
}
