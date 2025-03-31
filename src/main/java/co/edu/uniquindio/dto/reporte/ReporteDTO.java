package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoResulto;
import co.edu.uniquindio.model.enums.EstadoSeveridad;

import java.util.List;

public record ReporteDTO(

        String id,
        String titulo,
        String idUsuario,
        UbicacionDTO ubicacion,
        EstadoResulto estadoReporte,
        CategoriaDTO categoria,
        List<ComentarioDTO> comentarios,
        EstadoReporte verificado, // Admin verification
        List<String> fotos,
        int numeroImportancia,
        EstadoSeveridad severidad
) {
}
