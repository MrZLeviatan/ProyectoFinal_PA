package co.edu.uniquindio.dto.comentario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ComentarioDTO(
        String id,
        String contenido, // Contenido del comentario
        LocalDateTime fechaComentario, // Fecha en que se realizó el comentario
        String idUsuario, // Id del usuario que realizó el comentario
        String idReporte, // Id del reporte al que está asociado el comentario
        List<ComentarioDTO> comentarios // Lista de respuestas en formato DTO
) {
}
