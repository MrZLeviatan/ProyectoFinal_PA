package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.model.enums.Ciudad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para registrar un nuevo usuario en el sistema.
 *
 * @param nombre             Nombre del usuario (máximo 100 caracteres).
 * @param direccion          Dirección del usuario (máximo 100 caracteres).
 * @param ciudad             Ciudad de residencia.
 * @param email              Correo electrónico del usuario (máximo 50 caracteres).
 * @param password           Contraseña segura (entre 7 y 20 caracteres).
 */
public record RegistrarUsuarioDto(

        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(max = 100) String direccion,
        @NotNull Ciudad ciudad,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 7, max = 20) String password

) {
}

