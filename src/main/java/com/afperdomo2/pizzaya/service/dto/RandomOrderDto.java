package com.afperdomo2.pizzaya.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RandomOrderDto {
    @Schema(description = "ID del cliente que realiza el pedido", example = "1", required = true)
    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Positive(message = "El ID del cliente debe ser un valor positivo")
    private Long customerId;

    @Schema(description = "Tipo de pedido (e.g., D, C, S)", example = "D", required = true)
    @NotBlank(message = "El tipo de pedido no puede estar vacío")
    @Length(max = 1, message = "El tipo de pedido debe tener un máximo de 1 carácter")
    private String orderType;
}
