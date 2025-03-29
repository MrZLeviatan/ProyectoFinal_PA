package co.edu.uniquindio.dto.usuario;

import java.time.LocalDateTime;

public record CodigoValidacionDTO(

        String codigo, // Código generado para la validación
        LocalDateTime horaCreacion // Fecha y hora de expiración
) {
}
