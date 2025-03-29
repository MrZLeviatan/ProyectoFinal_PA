package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarComentarioDto(

        @NotBlank String idComentario,
        @NotBlank @Length(max = 100) String contenido

) {
}
