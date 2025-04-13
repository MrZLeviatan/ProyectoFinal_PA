package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.comentario.*;
import co.edu.uniquindio.dto.modeloDTO.CrearNotificacionDTO;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.ComentarioMapper;
import co.edu.uniquindio.model.documentos.Comentario;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.repositorios.ComentarioRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.services.ComentarioService;
import co.edu.uniquindio.services.EmailService;
import co.edu.uniquindio.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static co.edu.uniquindio.constants.MensajesError.COMENTARIO_NO_ENCONTRADO;
import static co.edu.uniquindio.constants.MensajesError.REPORTE_NO_ENCONTRADO;


@Service
@RequiredArgsConstructor
public class ComentarioServiceImplement implements ComentarioService {

    private final ComentarioRepo comentarioRepo;
    private final ComentarioMapper comentarioMapper;
    private final ReporteRepo reporteRepo;
    private final WebSocketNotificationService socketNotificationService;
    private final NotificacionServiceImplement notificacionServiceImplement;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    @Transactional
    @Override
    public void agregarComentario(RegistrarComentarioDto comentario) throws Exception {
        Comentario nuevoComentario = comentarioMapper.toComentario(comentario);
        nuevoComentario.setFechaComentario(LocalDateTime.now());
        nuevoComentario.setIdComentario(null);
        Reporte reporte = obtenerReportePorId(comentario.idReporte());
        Comentario cometarioGuardado=  comentarioRepo.save(nuevoComentario);
        reporte.getComentarios().add(cometarioGuardado);
        reporteRepo.save(reporte);

        String topic = "/topic/user/" + reporte.getIdUsuario() + "/reports";
        String titulo= "nuevo comentario";
        String cuerpo= "alguien comento tu reporte";
        NotificacionDTO noti = new NotificacionDTO(
                titulo,
                cuerpo,
                topic
        );
        socketNotificationService.notificarPorTopic(noti);

        UsuarioDTO remitente= usuarioService.obtenerUsuarioId(cometarioGuardado.getIdUsuario().toString());
        UsuarioDTO destinatario= usuarioService.obtenerUsuarioId(reporte.getIdUsuario().toString());
        CrearNotificacionDTO crearNotificacionDTO= new CrearNotificacionDTO(
                destinatario.email(),
                cuerpo,
                titulo,
                remitente.email()
        );
        notificacionServiceImplement.enviarNotificacion(crearNotificacionDTO);


        //envio de mensaje con lo que dice el comentario
        EmailDto emailDto = new EmailDto(
          titulo,
          comentario.contenido(), //agregamos el contenido
          destinatario.email()
        );

        emailService.enviarCorreo(emailDto);
    }

    @Override
    public void actualizarComentario(EditarComentarioDto comentario) throws Exception {
        Comentario comentarioExistente = obtenerComentarioPorId(comentario.idComentario());
        comentarioExistente.setContenido(comentario.contenido());
        comentarioRepo.save(comentarioExistente);

        //notificacion
        String topic = "/topic/user/" + comentarioExistente.getIdUsuario() + "/reports";
        String cuerpo = "has actualizado tu comentario";
        String titulo = "Actualizacion de comentario";
        NotificacionDTO noti = new NotificacionDTO(
                titulo,
                cuerpo,
                topic
        );
        socketNotificationService.notificarPorTopic(noti);

        UsuarioDTO destinatario= usuarioService.obtenerUsuarioId(comentarioExistente.getIdUsuario().toString());
        String remitente ="sistema";
        CrearNotificacionDTO crearNotificacionDTO= new CrearNotificacionDTO(
                destinatario.email(),
                cuerpo,
                titulo,
                remitente
        );
        notificacionServiceImplement.enviarNotificacion(crearNotificacionDTO);

    }

    @Override
    @Transactional
    public void eliminarComentario(EliminarComentarioDto comentario) throws Exception {
        Comentario comentarioAEliminar = obtenerComentarioPorId(comentario.idComentario());

        eliminarHijosRecursivos(comentarioAEliminar.getId());

        // Eliminar del reporte
        reporteRepo.findById(comentarioAEliminar.getIdReporte())
                .ifPresent(reporte -> {
                    reporte.getComentarios().removeIf(c -> c.getId().equals(comentarioAEliminar.getId()));
                    reporteRepo.save(reporte);
                });

        comentarioRepo.delete(comentarioAEliminar);
        //notificar
        String idAutorComentario = comentarioAEliminar.getIdUsuario().toString();
        String topic = "/topic/user/" + idAutorComentario + "/reports";
        String cuerpo = "has eliminado tu comentario";
        String titulo = "Eliminacion de comentario";
        NotificacionDTO noti = new NotificacionDTO(
                titulo,
                cuerpo,
                topic
        );
        socketNotificationService.notificarPorTopic(noti);

        String email= usuarioService.obtenerUsuarioId(comentarioAEliminar.getIdUsuario().toString()).email();
        String remitente ="sistema";
        CrearNotificacionDTO crearNotificacionDTO= new CrearNotificacionDTO(
                email,
                cuerpo,
                titulo,
                remitente
        );
        notificacionServiceImplement.enviarNotificacion(crearNotificacionDTO);


    }

    private void eliminarHijosRecursivos(ObjectId idPadre) {
        List<Comentario> hijos = comentarioRepo.findByIdComentario(idPadre);
        for (Comentario hijo : hijos) {
            eliminarHijosRecursivos(hijo.getId()); // Recursividad
            comentarioRepo.delete(hijo); // Elimina cada hijo
        }
    }

    @Override
    public ComentarioDTO buscarComentario(String idComentario) throws ElementoNoEncontradoException {
        Comentario comentario = obtenerComentarioPorId(idComentario);
        return comentarioMapper.toComentarioDTO(comentario);
    }
    @Override
    public List<ComentarioDTO> buscarComentariosReporte(String idReporte) throws ElementoNoEncontradoException {
        Reporte reporte = obtenerReportePorId(idReporte);
        return comentarioMapper.toComentarioDTOList(reporte.getComentarios());
    }
    @Override
    public void agregarRespuestaComentario(RespuestaComentarioDto respuestaComentario) throws Exception {
        Comentario comentarioPadre = obtenerComentarioPorId(respuestaComentario.idComentario());

        RegistrarComentarioDto dto = new RegistrarComentarioDto(
                respuestaComentario.contenido(),
                respuestaComentario.idReporte(),
                respuestaComentario.idUsuario()
        );

        Comentario respuesta = comentarioMapper.toComentario(dto);
        respuesta.setFechaComentario(LocalDateTime.now());
        respuesta.setIdComentario(comentarioPadre.getId());
        Comentario respuestaGuardada = comentarioRepo.save(respuesta);
        comentarioPadre.getComentarios().add(respuestaGuardada);
        comentarioRepo.save(comentarioPadre);

        //notificar

        String idAutorComentario = comentarioPadre.getIdUsuario().toString();
        String topic = "/topic/user/" + idAutorComentario + "/reports";
        String cuerpo= "Alguien respondió a tu comentario";
        String titulo = "Respuesta a tu comentario ";
        NotificacionDTO noti = new NotificacionDTO(
                titulo,
                cuerpo,
                topic
        );
        socketNotificationService.notificarPorTopic(noti);

        String email= usuarioService.obtenerUsuarioId(comentarioPadre.getIdUsuario().toString()).email();
        String remitente ="sistema";
        CrearNotificacionDTO crearNotificacionDTO= new CrearNotificacionDTO(
                email,
                cuerpo,
                titulo,
                remitente
        );
        notificacionServiceImplement.enviarNotificacion(crearNotificacionDTO);

    }
    @Override
    public List<ComentarioDTO> buscarRespuestaComentario(String idComentario) throws ElementoNoEncontradoException {
        Comentario comentario = obtenerComentarioPorId(idComentario);
        return comentarioMapper.toComentarioDTOList(comentario.getComentarios());
    }


    private Comentario obtenerComentarioPorId(String id) throws ElementoNoEncontradoException {
        return comentarioRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException(COMENTARIO_NO_ENCONTRADO + id));
    }

    private Reporte obtenerReportePorId(String id)throws ElementoNoEncontradoException {
        return reporteRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException(REPORTE_NO_ENCONTRADO + id));
    }
}