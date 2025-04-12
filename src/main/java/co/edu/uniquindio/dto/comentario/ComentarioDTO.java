package co.edu.uniquindio.dto.comentario;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que representa un comentario en el sistema.
 * Este objeto transporta los datos relacionados con un comentario,
 * incluyendo el contenido, la fecha de creación, el usuario que lo hizo,
 * el reporte asociado y cualquier comentario o respuesta en formato DTO.
 */
public record ComentarioDTO(
        String id,
        String contenido, // Contenido del comentario
        LocalDateTime fechaComentario, // Fecha en que se realizó el comentario
        String idUsuario, // Id del usuario que realizó el comentario
        String idReporte, // Id del reporte al que está asociado el comentario
        List<ComentarioDTO> comentarios // Lista de respuestas en formato DTO
) {
}
