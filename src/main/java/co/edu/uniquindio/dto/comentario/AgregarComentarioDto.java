package co.edu.uniquindio.dto.comentario;

public record AgregarComentarioDto(
        String idReporte,
        String idUsuario,
        String comentario,
        String fecha

) {
}
