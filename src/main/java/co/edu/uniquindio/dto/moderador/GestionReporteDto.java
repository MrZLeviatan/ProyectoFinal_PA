package co.edu.uniquindio.dto.moderador;

import co.edu.uniquindio.model.enums.EstadoReporte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * DTO utilizado para gestionar el reporte.
 * Contiene información sobre el estado anterior y actual del reporte,
 * el id del usuario que realiza la gestión, y el motivo de la gestión.
 * Todos los campos son obligatorios y algunos tienen restricciones de longitud.
 */
public record GestionReporteDto(

        @NotNull EstadoReporte estadoAnterior,
        @NotNull EstadoReporte estadoActual,
        @NotBlank String idUsuario,
        @NotBlank @Length(max = 200) String motivo,
        @NotBlank String idReporte
) {
}
