package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para registrar una respuesta a un comentario en el sistema.
 * Contiene los datos necesarios para crear una respuesta, como el contenido,
 * el comentario al que responde, el reporte al que está asociado, y el usuario
 * que realiza la respuesta.
 */
public record RespuestaComentarioDto(

        @NotBlank String idComentario,
        @NotBlank @Length(max = 100) String contenido,
        @NotBlank String idReporte,
        @NotBlank String idUsuario
) {
}
