package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.enums.Ciudad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para editar la información de un usuario.
 *
 * @param id        ID del usuario a editar.
 * @param nombre    Nuevo nombre del usuario (máximo 100 caracteres).
 * @param ciudad    Nueva ciudad del usuario.
 * @param direccion Nueva dirección del usuario (máximo 100 caracteres).
 */
public record EditarUsuarioDto(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 100) String direccion
) {
}

