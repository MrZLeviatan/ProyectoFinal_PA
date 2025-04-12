package co.edu.uniquindio.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO utilizado para activar una cuenta de usuario.
 *
 * @param id                Correo del usuario que desea activar la cuenta.
 * @param codigoActivacion  Código de validación ingresado por el usuario.
 */
public record ActivarCuentaDto(
        @NotBlank String id,
        @NotNull CodigoValidacionDTO codigoActivacion
) {
}
