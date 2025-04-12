package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para solicitar la eliminación de una cuenta de usuario.
 *
 * @param id        Identificador del usuario.
 * @param password  Contraseña del usuario para verificar su identidad.
 */
public record EliminarCuentaDto(
        @NotBlank String id,
        @NotBlank String password
) {
}

