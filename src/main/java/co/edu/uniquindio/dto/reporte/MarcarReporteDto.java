package co.edu.uniquindio.dto.reporte;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para marcar un reporte como importante por parte de un usuario.
 *
 * @param idUsuario ID del usuario que marca el reporte como importante.
 * @param idReporte ID del reporte que se desea marcar.
 */
public record MarcarReporteDto(

        @NotBlank String idUsuario,
        @NotBlank String idReporte

) {
}
