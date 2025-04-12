package co.edu.uniquindio.services.impl;

/**
 * DTO para la notificación.
 * Esta clase se usa para representar una notificación que se enviará a un grupo de usuarios.
 */
public record NotificacionDTO(
        String titulo,
        String cuerpo,
        String  topic
) {
}
