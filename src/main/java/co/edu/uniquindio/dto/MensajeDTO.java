package co.edu.uniquindio.dto;

/**
 * DTO genérico que representa un mensaje con posible error.
 *
 * @param error   Indica si ocurrió un error (true = error).
 * @param mensaje Contenido o información adicional del mensaje.
 * @param <T>     Tipo genérico del contenido del mensaje.
 */
public record MensajeDTO<T>(boolean error, T mensaje) {
}
