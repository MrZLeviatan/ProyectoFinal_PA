package co.edu.uniquindio.dto.moderador;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarCategoriaDto(

        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 200) String descripcion
) {



}
