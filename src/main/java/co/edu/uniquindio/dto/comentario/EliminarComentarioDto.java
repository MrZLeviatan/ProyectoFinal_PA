package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO utilizado para eliminar un comentario en el sistema.
 * Contiene los datos necesarios para realizar la eliminación de un comentario
 * en este caso el ID del comentario.
 */
public record EliminarComentarioDto(
        @NotBlank String idComentario
) {
}
