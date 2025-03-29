package co.edu.uniquindio.dto.reporte;

import jakarta.validation.constraints.NotBlank;

public record MarcarReporteResueltoDto(

        @NotBlank String idReporte,
        @NotBlank String idUsuario,
        @NotBlank String password
) {
}
