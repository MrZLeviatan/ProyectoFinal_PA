package co.edu.uniquindio.dto.moderador;

import co.edu.uniquindio.model.enums.EstadoReporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

// Sirve como creación del HistorialEstado
public record GestionReporteDto(

        @NotNull EstadoReporte estadoAnterior,
        @NotNull EstadoReporte estadoActual,
        @NotBlank String idUsuario,
        @NotBlank @Length(max = 200) String motivo
) {
}
