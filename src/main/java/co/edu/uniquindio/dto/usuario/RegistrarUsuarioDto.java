package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.enums.Ciudad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistrarUsuarioDto(

        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 100)String direccion,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password,
        @NotNull CodigoValidacionDTO codigoValidacionDTO
) {
}
