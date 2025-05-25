package co.edu.uniquindio.dto.modeloDTO;

import co.edu.uniquindio.model.enums.EstadoNotificacion;

import java.time.LocalDateTime;

public record MostrarNotificacionDTO(
        String id,
        String destinatario,
        String mensaje,
        LocalDateTime fecha,
        String remitente,
        String titulo,
        EstadoNotificacion estado
) {

}
