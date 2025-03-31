package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import java.util.List;

public record UsuarioDTO(
        String nombre,
        String direccion,
        Ciudad ciudad,
        String id,
        String email,
        List<NotificacionDTO> notificaciones,
        List<ReporteDTO> reportes,
        List<ReporteDTO> listaReportesFavorito
) {
}
