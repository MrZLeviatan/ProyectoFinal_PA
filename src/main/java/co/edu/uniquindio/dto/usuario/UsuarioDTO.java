package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.enums.Ciudad;

import java.util.List;

public record UsuarioDTO(
        String nombre,
        String direccion,
        Ciudad ciudad,
        String id,
        String email,
        List<NotificacionDTOM> notificaciones,
        List<ReporteDTO> reportes,
        List<ReporteDTO> listaReportesFavorito
) {
}
