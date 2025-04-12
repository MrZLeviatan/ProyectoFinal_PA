package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para restablecer la contraseña de un usuario.
 *
 * @param email     Correo electrónico del usuario.
 * @param password  Nueva contraseña del usuario (entre 7 y 20 caracteres).
 */
public record RestablecerPasswordDto(
        @NotBlank String email,
        @NotBlank @Length(min = 7, max = 20) String password
) {
}
