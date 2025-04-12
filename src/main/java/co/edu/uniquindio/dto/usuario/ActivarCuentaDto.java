package co.edu.uniquindio.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivarCuentaDto(

        @NotBlank String id,    // Correo perteneciente al usuario a activar
        @NotNull CodigoValidacionDTO codigoActivacion // Código ingresado por el cliente
) {

}
