package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.comentario.*;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.ComentarioMapper;
import co.edu.uniquindio.model.documentos.Comentario;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.repositorios.ComentarioRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public void agregarComentario(RegistrarComentarioDto comentario) throws ElementoNoEncontradoException  {
        Comentario nuevoComentario = comentarioMapper.toComentario(comentario);
        Reporte reporte = obtenerReportePorId(comentario.idReporte());
        Comentario cometarioGuardado=  comentarioRepo.save(nuevoComentario);
        reporte.getComentarios().add(cometarioGuardado);
        reporteRepo.save(reporte);
//        String titulo = "Nuevo comentario";
//        String descripcion = "Se agregó un nuevo comentario al reporte " + comentario.idReporte();
//        String topic = "/topic/user/" + comentario.idUsuario() + "/reports";;
//        socketNotificationService.notificarClienteEspecifico(comentario.idUsuario(),new NotificacionDTO());
    }

    @Override
    public void actualizarComentario(EditarComentarioDto comentario) throws ElementoNoEncontradoException {
        Comentario comentarioExistente = obtenerComentarioPorId(comentario.idComentario());
        comentarioExistente.setContenido(comentario.contenido());
        comentarioRepo.save(comentarioExistente);
    }

    @Override
    public void eliminarComentario(EliminarComentarioDto comentario) throws ElementoNoEncontradoException {
        Comentario comentarioAEliminar = obtenerComentarioPorId(comentario.idComentario());
        eliminarHijos(comentarioAEliminar);
        Optional<Reporte> reporteOpt = reporteRepo.findById(comentarioAEliminar.getIdReporte());
        if (reporteOpt.isPresent()) {
            Reporte reporte = reporteOpt.get();
            reporte.getComentarios().removeIf(c -> c.getId().equals(comentarioAEliminar.getId()));
            reporteRepo.save(reporte);
        }
        comentarioRepo.delete(comentarioAEliminar);
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
    public void agregarRespuestaComentario(RespuestaComentarioDto respuestaComentario) throws ElementoNoEncontradoException {
        Comentario comentarioPadre = obtenerComentarioPorId(respuestaComentario.idComentario());

        RegistrarComentarioDto dto = new RegistrarComentarioDto(
                respuestaComentario.contenido(),
                respuestaComentario.idReporte(),
                respuestaComentario.idUsuario()
        );

        Comentario respuesta = comentarioMapper.toComentario(dto);
        respuesta.setIdComentario(comentarioPadre.getId());

        Comentario respuestaGuardada = comentarioRepo.save(respuesta);
        comentarioPadre.getComentarios().add(respuestaGuardada);
        comentarioRepo.save(comentarioPadre);
    }
    @Override
    public List<ComentarioDTO> buscarRespuestaComentario(String idComentario) throws ElementoNoEncontradoException {
        Comentario comentario = obtenerComentarioPorId(idComentario);
        return comentarioMapper.toComentarioDTOList(comentario.getComentarios());
    }

    private void eliminarHijos(Comentario comentarioPadre) {
        List<Comentario> hijos = comentarioRepo.findByIdComentario(comentarioPadre.getId());
        for (Comentario hijo : hijos) {
            eliminarHijos(hijo); // Recursividad: elimina los hijos del hijo
            comentarioRepo.delete(hijo); // Elimina el hijo actual
        }
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