package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.enums.Ciudad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record EditarUsuarioDto(
        @NotBlank String id,
        @NotBlank @Length(max = 100)String nombre,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 100) String direccion
) {
}
