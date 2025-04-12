package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.model.enums.EstadoReporte;

import java.time.LocalDateTime;

/**
 * DTO que representa un cambio de estado dentro del historial de un reporte.
 * Almacena información sobre el cambio, quién lo realizó y el motivo.
 *
 * @param fecha Fecha en la que se realizó el cambio de estado.
 * @param estadoAnterior Estado anterior del reporte antes del cambio.
 * @param estadoReporte Nuevo estado del reporte después del cambio.
 * @param usuario Usuario que realizó el cambio de estado.
 * @param motivoCambio Razón o justificación del cambio de estado.
 */
public record HistorialEstadoDTO(
        LocalDateTime fecha,
        EstadoReporte estadoAnterior,
        EstadoReporte estadoReporte,
        UsuarioDTO usuario,
        String motivoCambio
) {
}

