package co.edu.uniquindio.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * DTO que representa un código de validación temporal.
 *
 * @param codigo        Código alfanumérico generado para la validación.
 * @param horaCreacion  Fecha y hora en que fue creado el código.
 */
public record CodigoValidacionnnDTO(

        String codigo, // Código generado para la validación
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime horaCreacion // Fecha y hora de expiración
) {
}
