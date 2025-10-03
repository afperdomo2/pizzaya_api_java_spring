package com.afperdomo2.pizzaya.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdatePizzaPriceDto {
    @Schema(description = "ID de la pizza", example = "1", required = true)
    private Long id;

    @Schema(description = "Nuevo precio de la pizza", example = "14.99", required = true)
    private BigDecimal newPrice;
}
