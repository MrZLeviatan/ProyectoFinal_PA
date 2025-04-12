package co.edu.uniquindio.dto;

/**
 * DTO que representa un error de validación en un campo.
 *
 * @param field           Nombre del campo con error.
 * @param defaultMessage  Mensaje por defecto del error.
 */
public record ValidacionDTO(
        String field,
        String defaultMessage
) {
}

