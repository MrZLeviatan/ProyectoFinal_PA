package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.modeloDTO.CrearNotificacionDTO;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;

import java.util.List;

public interface NotificacionService {

    /**
     * Envía una notificación a un usuario específico.
     *
     * @param notificacionDTOM: Objeto que contiene la información de la notificación a enviar.
     */
    void enviarNotificacion(CrearNotificacionDTO notificacionDTOM);

    /**
     * Recupera la lista de notificaciones recibidas por un usuario.
     * @param idUsuario: Identificador único del usuario.
     * @return Lista de objetos NotificacionDTOM correspondientes a las notificaciones del usuario.
     */
    List<NotificacionDTOM> leerNotificaciones(String idUsuario);

    /**
     * Busca una notificación en el sistema usando su identificador único.
     * @param idNotificacion: Identificador único de la notificación.
     * @return Objeto NotificacionDTOM con la información de la notificación encontrada.
     */
    NotificacionDTOM buscarNotificacion(String idNotificacion);

    /**
     * Elimina una notificación del sistema a partir de su identificador único.
     * @param idNotificacion: Identificador único de la notificación a eliminar.
     */

    void EliminarNotificacion(String idNotificacion);

}

