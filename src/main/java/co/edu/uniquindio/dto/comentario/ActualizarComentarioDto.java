package co.edu.uniquindio.dto.comentario;

public record ActualizarComentarioDto(
        String id, //id del comentario ah actualizar
        String contenido, //el cambio que realiza en el contenido
        String idReporte// la id del reporte al que pertenece

) {
}
