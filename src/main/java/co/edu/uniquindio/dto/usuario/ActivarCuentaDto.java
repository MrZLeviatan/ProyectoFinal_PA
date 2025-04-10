package co.edu.uniquindio.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record ActivarCuentaDto(

        @NotBlank String id,    // Correo perteneciente al usuario a activar
        @NotBlank CodigoValidacionDTO codigoActivacion // Código ingresado por el cliente
) {

}
