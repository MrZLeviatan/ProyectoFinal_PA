package co.edu.uniquindio.dto.usuario;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.enums.Ciudad;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;

import java.util.List;

/**
 * DTO que representa la información pública de un usuario del sistema.
 *
 * @param nombre                   Nombre del usuario.
 * @param direccion                Dirección del usuario.
 * @param ciudad                   Ciudad del usuario.
 * @param id                       Identificador único del usuario.
 * @param email                    Correo electrónico del usuario.
 * @param notificaciones           Lista de notificaciones recibidas.
 * @param reportes                 Lista de reportes creados por el usuario.
 * @param listaReportesFavorito    Lista de reportes marcados como favoritos por el usuario.
 */
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

