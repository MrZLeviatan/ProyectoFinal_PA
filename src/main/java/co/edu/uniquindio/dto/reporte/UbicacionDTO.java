package co.edu.uniquindio.dto.reporte;

/**
 * DTO que representa la ubicación geográfica de un reporte.
 *
 * @param latitud Coordenada de latitud.
 * @param altitud Coordenada de altitud.
 * @param radio   Radio de afectación en metros.
 */
public record UbicacionDTO(
        float latitud,
        float altitud,
        float radio
) {
}

