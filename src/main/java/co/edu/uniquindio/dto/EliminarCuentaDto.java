package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record EliminarCuentaDto(

        @NotBlank String id, // atributo para identificar el usuario
            @NotBlank String password //contraseña para determinar si es el usuario el que realiza la accion
) {
}
