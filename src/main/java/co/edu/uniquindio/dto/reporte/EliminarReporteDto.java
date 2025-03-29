package co.edu.uniquindio.dto.reporte;

import jakarta.validation.constraints.NotBlank;

public record EliminarReporteDto(

        @NotBlank String idReporte,
        @NotBlank String password
) {
}
