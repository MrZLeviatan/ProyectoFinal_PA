package co.edu.uniquindio.model.vo;

import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoReporte;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Representa el historial de cambios de estado de un reporte, incluyendo la fecha, el estado actual,
 * el usuario que realizó el cambio y el motivo del cambio.
 */

@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstado {

    private LocalDateTime fecha;
    private EstadoReporte estadoActual;
    private Usuario usuario;
    private String motivoCambio;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoReporte getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoReporte estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMotivoCambio() {
        return motivoCambio;
    }

    public void setMotivoCambio(String motivoCambio) {
        this.motivoCambio = motivoCambio;
    }
}


