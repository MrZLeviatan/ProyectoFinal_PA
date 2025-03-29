package co.edu.uniquindio.dto.reporte;

import jakarta.validation.constraints.NotBlank;

public record MarcarReporteDto(

        @NotBlank String idUsuario, // Id del usuario que marca el reporte como importante
        @NotBlank String idReporte
) {
}
