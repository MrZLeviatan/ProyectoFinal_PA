package co.edu.uniquindio.dto.modeloDTO;

import co.edu.uniquindio.model.enums.EstadoNotificacion;

public record NotificacionDTO(
        String id,
        String idDestinatario,
        String mensaje,
        String fecha,
        String idRemitente,
        String titulo,
        String ubicacionReporte,
        String IDReporteAsociado,
        EstadoNotificacion estadoNotificacion
) {
}
