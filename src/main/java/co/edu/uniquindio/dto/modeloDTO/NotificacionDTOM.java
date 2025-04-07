package co.edu.uniquindio.dto.modeloDTO;

import co.edu.uniquindio.dto.reporte.UbicacionDTO;
import co.edu.uniquindio.model.enums.EstadoNotificacion;

import java.time.LocalDateTime;

public record NotificacionDTOM(
        String CorreoDestinatario,
        String mensaje,
        LocalDateTime fecha,
        String CorreoRemitente,
        String titulo,
        UbicacionDTO ubicacionReporte,
        String IDReporteAsociado,
        EstadoNotificacion estadoNotificacion
) {
}
