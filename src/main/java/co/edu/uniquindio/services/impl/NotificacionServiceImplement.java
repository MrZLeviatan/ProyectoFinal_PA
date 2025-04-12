package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.model.documentos.Notificacion;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.NotificacionService;

import java.util.List;
import java.util.Optional;

public class NotificacionServiceImplement implements NotificacionService {

    UsuarioRepo usuarioRepo;

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

    @Override
    public List<NotificacionDTOM> leerNotificaciones(String idUsuario) {
        return List.of();
    }

    @Override
    public NotificacionDTOM buscarNotificacion(String idNotificacion) {
        return null;
    }

    @Override
    public void EliminarNotificacion(String idNotificacion) {

    }


}
