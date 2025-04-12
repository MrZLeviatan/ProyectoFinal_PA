package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.model.documentos.Notificacion;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.NotificacionService;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de notificaciones.
 * Esta clase se encarga de gestionar las notificaciones en el sistema, como su envío y eliminación.
 */
public class NotificacionServiceImplement implements NotificacionService {

    UsuarioRepo usuarioRepo;

    /**
     * Método para enviar una notificación a un usuario específico.
     * Se busca al usuario por su correo y se crea una notificación para él.
     *
     * @param notificacionDTOM Objeto DTO con los detalles de la notificación a enviar.
     */
    @Override
    public void enviarNotificacion(NotificacionDTOM notificacionDTOM) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(notificacionDTOM.CorreoDestinatario());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Notificacion notificacion = new Notificacion();

        }else {
            throw new ElementoNoEncontradoException("no existe el usuario con correo "+ notificacionDTOM.CorreoDestinatario());
        }
    }

    /**
     * Método para leer las notificaciones de un usuario.
     *
     * @param idUsuario El ID del usuario cuyo historial de notificaciones se quiere consultar.
     * @return Una lista vacía de notificaciones (por implementar).
     */
    @Override
    public List<NotificacionDTOM> leerNotificaciones(String idUsuario) {
        return List.of();
    }

    /**
     * Método para buscar una notificación por su ID.
     *
     * @param idNotificacion El ID de la notificación que se quiere buscar.
     * @return null (por implementar).
     */
    @Override
    public NotificacionDTOM buscarNotificacion(String idNotificacion) {
        return null;
    }

    /**
     * Método para eliminar una notificación por su ID.
     *
     * @param idNotificacion El ID de la notificación que se quiere eliminar.
     */
    @Override
    public void EliminarNotificacion(String idNotificacion) {

    }
}
