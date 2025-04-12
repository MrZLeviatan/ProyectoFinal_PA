package co.edu.uniquindio.dto.modeloDTO;

/**
 * DTO utilizado para crear una notificación en el sistema.
 * Contiene la información necesaria para enviar una notificación por correo electrónico,
 * incluyendo el destinatario, el mensaje, el título de la notificación y el remitente.
 */
public record CrearNotificacionDTO(
        String CorreoDestinatario,
        String mensaje,
        String titulo,
        String correoRemitente
) {
}
