package co.edu.uniquindio.dto.comentario;

public record AgregarRespuestaComentarioDto(
        String idReporte, // a que reporte pertence
        String idComentario, //que comentario esta respondiendo
        String idUsuario, //quien comento
        String comentario,//que comento
        String fecha // fecha en la que comento
) {
}
