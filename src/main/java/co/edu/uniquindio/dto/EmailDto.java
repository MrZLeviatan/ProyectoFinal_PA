package co.edu.uniquindio.dto;

/**
 * DTO que representa un correo electrónico a enviar.
 *
 * @param asunto       Asunto del correo.
 * @param cuerpo       Cuerpo o contenido del mensaje.
 * @param destinatario Correo electrónico del destinatario.
 */
public record EmailDto(
        String asunto,
        String cuerpo,
        String destinatario
) {
}
