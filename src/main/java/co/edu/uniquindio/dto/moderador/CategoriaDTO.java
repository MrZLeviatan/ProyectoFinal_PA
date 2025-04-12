package co.edu.uniquindio.dto.moderador;

/**
 * DTO que representa una categoría en el sistema.
 * Contiene el identificador, el nombre y la descripción de la categoría.
 */
public record CategoriaDTO(
        String id,
        String nombre,
        String descripcion
) {
}
