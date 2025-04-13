package co.edu.uniquindio.dto.modeloDTO;

import co.edu.uniquindio.model.enums.EstadoNotificacion;

import java.time.LocalDateTime;

/**
 * DTO que representa una notificación enviada en el sistema.
 * Contiene los detalles de la notificación, como el destinatario, el mensaje,
 * la fecha de envío, el remitente, el título, la ubicación asociada al reporte,
 * el ID del reporte asociado y el estado de la notificación.
 */
public record NotificacionDTOM(
        String destinatario,
        String mensaje,
        LocalDateTime fecha,
        String remitente,
        String titulo,
        EstadoNotificacion estado
) {
}
