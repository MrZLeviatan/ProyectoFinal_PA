package co.edu.uniquindio.dto.moderador;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para editar una categoría ya existente.
 * Contiene el nombre y la descripción de la categoría.
 * Ambos campos son obligatorios y tienen restricciones de longitud.
 */
public record EditarCategoriaDto(

        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 200) String descripcion
) {



}
