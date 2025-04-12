package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoResuelto;
import co.edu.uniquindio.model.enums.EstadoSeveridad;

import java.util.List;

/**
 * DTO que representa la información completa de un reporte.
 *
 * @param id                 ID único del reporte.
 * @param titulo             Título del reporte.
 * @param idUsuario          ID del usuario que creó el reporte.
 * @param ubicacion          Ubicación del incidente.
 * @param estadoReporte      Estado actual del reporte (resuelto o no).
 * @param categoria          Categoría del incidente.
 * @param comentarios        Lista de comentarios asociados al reporte.
 * @param verificado         Estado de verificación por parte del administrador.
 * @param fotos              Lista de URLs de las imágenes del reporte.
 * @param numeroImportancia  Número de veces que ha sido marcado como importante.
 * @param severidad          Nivel de severidad del reporte.
 */
public record ReporteDTO(

        String id,
        String titulo,
        String idUsuario,
        UbicacionDTO ubicacion,
        EstadoResuelto estadoReporte,
        CategoriaDTO categoria,
        List<ComentarioDTO> comentarios,
        EstadoReporte verificado,
        List<String> fotos,
        int numeroImportancia,
        EstadoSeveridad severidad

) {
}

