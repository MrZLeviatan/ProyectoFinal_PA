package co.edu.uniquindio.dto.moderador;

import co.edu.uniquindio.model.enums.Ciudad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para editar un Moderador.
 * Contiene el nombre, la ciudad y la direccion.
 * Todos los campos son obligatorios  e incluso
 * tienen restricciones de longitud.
 */
public record EditarModeradorDto(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 100) String direccion
) {
}
