package co.edu.uniquindio.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistrarComentarioDto(

        @NotBlank @Length(max = 100) String contenido,
        @NotBlank String idReporte,
        @NotBlank String idUsuario

) {
}
