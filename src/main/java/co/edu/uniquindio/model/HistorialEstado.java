package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.EstadoReporte;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class HistorialEstado {

    private LocalDateTime fecha;
    private EstadoReporte estadoAnterior,estadoReporte;
    private Usuario usuario;

    private String motivoCambio;

}
