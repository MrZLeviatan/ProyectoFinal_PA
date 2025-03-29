package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoReporte;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstado {

    private LocalDateTime fecha;
    private EstadoReporte estadoAnterior;
    private EstadoReporte estadoActual;
    private Usuario usuario;
    private String motivoCambio;

}
