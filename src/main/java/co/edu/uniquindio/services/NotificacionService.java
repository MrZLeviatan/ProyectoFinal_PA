package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;

import java.util.List;

public interface NotificacionService {
    void enviarNotificacion(NotificacionDTOM notificacionDTOM);
    List<NotificacionDTOM> leerNotificaciones(String idUsuario);
    NotificacionDTOM buscarNotificacion(String idNotificacion);
    void EliminarNotificacion(String idNotificacion);

}
