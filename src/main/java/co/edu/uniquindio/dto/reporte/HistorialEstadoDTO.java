package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.model.enums.EstadoReporte;

import java.time.LocalDateTime;

public record HistorialEstadoDTO(
        LocalDateTime fecha, // Fecha del cambio de estado
        EstadoReporte estadoAnterior, // Estado antes del cambio
        EstadoReporte estadoReporte, // Nuevo estado después del cambio
        UsuarioDTO usuario, // Usuario que realizó el cambio
        String motivoCambio // Razón del cambio de estado
) {
}
