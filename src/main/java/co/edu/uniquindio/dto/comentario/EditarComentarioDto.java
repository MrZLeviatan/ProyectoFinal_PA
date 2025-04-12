package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para editar un comentario en el sistema.
 * Contiene los datos necesarios para realizar la actualización de un comentario
 * como el ID del comentario y el nuevo contenido.
 */
public record EditarComentarioDto(
        @NotBlank String idComentario,
        @NotBlank @Length(max = 100) String contenido
) {
}
