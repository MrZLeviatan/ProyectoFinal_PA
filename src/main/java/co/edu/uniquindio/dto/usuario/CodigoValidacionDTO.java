package co.edu.uniquindio.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CodigoValidacionDTO(

        String codigo, // Código generado para la validación
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime horaCreacion // Fecha y hora de expiración
) {
}
