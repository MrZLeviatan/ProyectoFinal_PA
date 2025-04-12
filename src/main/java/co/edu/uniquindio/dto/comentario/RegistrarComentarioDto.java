package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para registrar un nuevo comentario en el sistema.
 * Contiene los datos necesarios para crear un comentario, como el contenido,
 * el reporte al que está asociado, y el usuario que realiza el comentario.
 */
public record RegistrarComentarioDto(

        @NotBlank @Length(max = 100) String contenido,
        @NotBlank String idReporte,
        @NotBlank String idUsuario

) {
}
