package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
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
        String password,
        EstadoUsuario estadoUsuario,
        Rol rol,
        List<NotificacionDTOM> notificaciones,
        List<ReporteDTO> reportes,
        List<ReporteDTO> listaReportesFavorito
) {
}
