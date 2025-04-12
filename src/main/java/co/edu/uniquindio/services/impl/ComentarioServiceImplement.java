package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.comentario.*;
import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.ComentarioMapper;
import co.edu.uniquindio.model.documentos.Comentario;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.repositorios.ComentarioRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImplement implements ComentarioService {
    @Autowired
    ComentarioRepo comentarioRepo;
    @Autowired
    ComentarioMapper comentarioMapper;
    @Autowired
    ReporteRepo reporteRepo;


    @Override
    public void agregarComentario(RegistrarComentarioDto comentario) throws Exception {
        Comentario nuevoComentario = comentarioMapper.toComentario(comentario);
        Optional<Reporte> reporte= reporteRepo.findById(new ObjectId(comentario.idReporte()));
        if (reporte.isPresent()) {
            reporte.get().getComentarios().add(nuevoComentario);
            reporteRepo.save(reporte.get());
            comentarioRepo.save(nuevoComentario);
        }else {
            throw new ElementoNoEncontradoException("no existe el reporte con id "+ comentario.idReporte());
        }
    }

    @Override
    public void actualizarComentario(EditarComentarioDto comentario) throws Exception {
        Optional<Comentario>comentarioEditar= comentarioRepo.findById(new ObjectId(comentario.idComentario()));
        if (comentarioEditar.isPresent()) {
            Comentario comentario1 = comentarioEditar.get();
            comentario1.setContenido(comentario.contenido());
            comentarioRepo.save(comentario1);
        }else {
            throw new ElementoNoEncontradoException("no existe el comentario con id"+ comentario.idComentario());
        }
    }

    @Override
    public void eliminarComentario(EliminarComentarioDto comentario) throws Exception {
        Optional<Comentario>comentarioEditar= comentarioRepo.findById(new ObjectId(comentario.idComentario()));
        if (comentarioEditar.isPresent()) {
            Comentario comentario1 = comentarioEditar.get();
            Optional<Reporte> reporte = reporteRepo.findById(comentario1.getIdReporte());
            if (reporte.isPresent()) {
                reporte.get().getComentarios().removeIf(c-> c.getIdComentario().equals(comentario1.getIdComentario()));
                reporteRepo.save(reporte.get());
                if(!comentario1.getComentarios().isEmpty()) {
                    eliminarHijos(comentario1);
                }else {
                    comentarioRepo.delete(comentario1);
                }
            }
        }else {
            throw new ElementoNoEncontradoException("no existe el comentario con id"+ comentario.idComentario());
        }
    }



    @Override
    public ComentarioDTO buscarComentario(String idComentario) throws Exception {
        Optional<Comentario> comentario = comentarioRepo.findById(new ObjectId(idComentario));
        if (comentario.isPresent()) {
            return comentarioMapper.toComentarioDTO(comentario.get());
        }else {
            throw new ElementoNoEncontradoException("no existe el comentario con id"+ idComentario);
        }
    }

    @Override
    public List<ComentarioDTO> buscarComentariosReporte(String idReporte) throws Exception {
        Optional<Reporte> reporte = reporteRepo.findById(new ObjectId(idReporte));
        if (reporte.isPresent()) {
            Reporte reporte1 = reporte.get();
            List<Comentario> comentarios = reporte1.getComentarios();
            return comentarioMapper.toComentarioDTOList(comentarios);
        }else{
            throw new ElementoNoEncontradoException("no existe el comentario con id"+ idReporte);
        }
    }


    @Override
    public void agregarRespuestaComentario(RespuestaComentarioDto respuestaComentario) throws Exception {
        ObjectId idPadre = new ObjectId(respuestaComentario.idComentario());
        Optional<Comentario> comentarioOpt = comentarioRepo.findById(idPadre);

        if (comentarioOpt.isEmpty()) {
            throw new ElementoNoEncontradoException("No existe el comentario con id " + respuestaComentario.idComentario());
        }

        Comentario comentarioPadre = comentarioOpt.get();

        RegistrarComentarioDto registrarComentarioDto = new RegistrarComentarioDto(
                respuestaComentario.contenido(),
                respuestaComentario.idReporte(),
                respuestaComentario.idUsuario()
        );

        Comentario respuesta = comentarioMapper.toComentario(registrarComentarioDto);
        respuesta.setIdComentario(idPadre);

        Comentario respuestaGuardada = comentarioRepo.save(respuesta);
        comentarioPadre.getComentarios().add(respuestaGuardada);

        comentarioRepo.save(comentarioPadre);
    }


    @Override
    public List<ComentarioDTO> buscarRespuestaComentario(String idComentario) throws Exception {
        Optional<Comentario> comentarioOpt = comentarioRepo.findById(new ObjectId(idComentario));
        if (comentarioOpt.isPresent()) {
            List<Comentario> comentarios = comentarioOpt.get().getComentarios();
            return comentarioMapper.toComentarioDTOList(comentarios);
        }else {
            throw new ElementoNoEncontradoException("No existe el comentario con id"+ idComentario);
        }
    }


    private void eliminarHijos(Comentario comentarioPadre) {
        List<Comentario> hijos = comentarioPadre.getComentarios();

        for (Comentario hijo : hijos) {
            Optional<Comentario> hijoCompleto = comentarioRepo.findById(hijo.getId());
            hijoCompleto.ifPresent(this::eliminarHijos); // llamada recursiva
            hijoCompleto.ifPresent(comentarioRepo::delete);
        }
    }
}