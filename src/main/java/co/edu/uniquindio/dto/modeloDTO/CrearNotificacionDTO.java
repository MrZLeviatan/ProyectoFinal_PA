package co.edu.uniquindio.dto.modeloDTO;

public record CrearNotificacionDTO(
        String CorreoDestinatario,
        String mensaje,
        String titulo,
        String correoRemitente
) {
}
