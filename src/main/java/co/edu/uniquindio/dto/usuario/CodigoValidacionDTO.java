package co.edu.uniquindio.dto.usuario;

import java.time.LocalDateTime;

/**
 * DTO que representa un código de validación temporal.
 *
 * @param codigo        Código alfanumérico generado para la validación.
 * @param horaCreacion  Fecha y hora en que fue creado el código.
 */
public record CodigoValidacionDTO(

        String codigo,
        LocalDateTime horaCreacion

) {
}
