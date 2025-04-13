package co.edu.uniquindio.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO utilizado para activar una cuenta de usuario.
 *
 * @param email                Correo del usuario que desea activar la cuenta.
 * @param codigoActivacion  Código de validación ingresado por el usuario.
 */
public record ActivarCuentaDto(
        @NotBlank String email,
        @NotNull CodigoValidacionDTO codigoActivacion
) {
}
