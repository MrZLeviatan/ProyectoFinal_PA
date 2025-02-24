package co.edu.uniquindio.dto.defaul;

public record EnviarNotificacionDto(
        String id, //a quien le enviaremos la notificación
        String mensaje //el contenido de la notificacíón
        // String id  // existe si una persona es la causa de notificación (x persona publico esto en tu localidad)

) {
}
