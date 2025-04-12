package co.edu.uniquindio.model.vo;

import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoReporte;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Representa el historial de cambios de estado de un reporte, incluyendo la fecha, el estado actual,
 * el usuario que realizó el cambio y el motivo del cambio.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstado {

    private LocalDateTime fecha;
    private EstadoReporte estadoActual;
    private Usuario usuario;
    private String motivoCambio;

}
