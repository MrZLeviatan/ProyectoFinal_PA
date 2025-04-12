package co.edu.uniquindio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para realizar el inicio de sesión en el sistema.
 *
 * @param email     Correo electrónico del usuario.
 * @param password  Contraseña del usuario (mínimo 7 caracteres).
 */
public record LoginDto(
        @NotBlank @Email String email,
        @NotBlank @Length(min = 7) String password
) {
}

