package co.edu.uniquindio.dto.reporte;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para eliminar un reporte del sistema.
 * Requiere la validación de la contraseña del usuario.
 *
 * @param idReporte ID del reporte que se desea eliminar.
 * @param password Contraseña del usuario para confirmar la acción.
 */
public record EliminarReporteDto(
        @NotBlank String idReporte,
        @NotBlank String password
) {
}
