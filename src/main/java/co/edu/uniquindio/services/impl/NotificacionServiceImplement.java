package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.constants.MensajesError;
import co.edu.uniquindio.dto.modeloDTO.CrearNotificacionDTO;
import co.edu.uniquindio.dto.modeloDTO.MostrarNotificacionDTO;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.NotificacionMapper;
import co.edu.uniquindio.model.documentos.Notificacion;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.repositorios.NotificacionRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Implementación del servicio de notificaciones.
 * Esta clase se encarga de gestionar las notificaciones en el sistema, como su envío y eliminación.
 */

@Service
@RequiredArgsConstructor
public class NotificacionServiceImplement implements NotificacionService {

    private final UsuarioRepo usuarioRepo;
    private final NotificacionRepo notificacionRepo;
    private final NotificacionMapper notificacionMapper;

    /**
     * Método para enviar una notificación a un usuario específico.
     * Se busca al usuario por su correo y se crea una notificación para él.
     *
     * @param crearNotificacionDTO Objeto DTO con los detalles de la notificación a enviar.
     */
    @Override
    public void enviarNotificacion(CrearNotificacionDTO crearNotificacionDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(crearNotificacionDTO.destinatario());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Notificacion notificacion = notificacionMapper.CrearNotificacionDTOtoNotificacion(crearNotificacionDTO);
            Notificacion notificacionGuardar = notificacionRepo.save(notificacion);
            usuario.getNotificaciones().add(notificacionGuardar);
            usuarioRepo.save(usuario);

        }else {
            throw new ElementoNoEncontradoException("no existe el usuario con correo "+ crearNotificacionDTO.destinatario());
        }
    }

    /**
     * Método para leer las notificaciones de un usuario.
     *
     * @param idUsuario El ID del usuario cuyo historial de notificaciones se quiere consultar.
     * @return Una lista vacía de notificaciones (por implementar).
     */
    @Override
    public List<MostrarNotificacionDTO> leerNotificaciones(String idUsuario) {
        Usuario usuario= obtenerUsuario(idUsuario);
        return notificacionMapper.toMostrarNotificacionDTOList(usuario.getNotificaciones());
    }


    /**
     * Método para buscar una notificación por su ID.
     *
     * @param idNotificacion El ID de la notificación que se quiere buscar.
     * @return null (por implementar).
     */
    @Override
    public NotificacionDTOM buscarNotificacion(String idNotificacion) {
        if(!ObjectId.isValid(idNotificacion)){
            throw new  ElementoNoEncontradoException(MensajesError.NOTIFICACION_NO_ENCONTRADO);
        }

        Optional<Notificacion> notificacionOptional= notificacionRepo.findById(new ObjectId(idNotificacion));
        if (notificacionOptional.isEmpty()) {
            throw new  ElementoNoEncontradoException(MensajesError.NOTIFICACION_NO_ENCONTRADO);
        }

        Notificacion notificacion = notificacionOptional.get();
        return notificacionMapper.toNotificacionDTO(notificacion);
    }

    /**
     * Método para eliminar una notificación por su ID.
     *
     * @param idNotificacion El ID de la notificación que se quiere eliminar.
     */
    @Override
    public void EliminarNotificacion(String idNotificacion) {
        if(!ObjectId.isValid(idNotificacion)){
            throw new  ElementoNoEncontradoException(MensajesError.NOTIFICACION_NO_ENCONTRADO);
        }
        Optional<Notificacion> notificacionOptional= notificacionRepo.findById(new ObjectId(idNotificacion));
        if (notificacionOptional.isEmpty()) {
            throw new  ElementoNoEncontradoException(MensajesError.NOTIFICACION_NO_ENCONTRADO);
        }

        Usuario usuario= obtenerUsuarioPorEmail(notificacionOptional.get().getDestinatario());
        usuario.getNotificaciones().stream().filter(notificacion -> notificacion.getId().equals(idNotificacion));

        notificacionRepo.delete(notificacionOptional.get());
        usuarioRepo.save(usuario);
    }

    private Usuario obtenerUsuarioPorEmail(String destinatario) {
        return usuarioRepo.findByEmail(destinatario).get();
    }


    private Usuario obtenerUsuario(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO);
        }
        return usuarioRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException(MensajesError.USARIO_NO_ENCONTRADO));
    }


}
