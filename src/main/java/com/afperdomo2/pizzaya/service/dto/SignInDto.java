package com.afperdomo2.pizzaya.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInDto {
    @Schema(description = "Nombre de usuario del usuario", example = "admin")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @Schema(description = "Contraseña del usuario", example = "admin123")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
