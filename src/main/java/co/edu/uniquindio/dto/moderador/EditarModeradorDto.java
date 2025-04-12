package co.edu.uniquindio.dto.moderador;

import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record EditarModeradorDto(
        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 100) String direccion
) {
}
