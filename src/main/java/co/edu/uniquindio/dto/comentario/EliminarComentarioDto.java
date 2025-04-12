package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;

public record EliminarComentarioDto(

        @NotBlank String idComentario
) {
}
