package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.reporte.*;

import co.edu.uniquindio.exeptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exeptions.PermisoDenegadoException;
import co.edu.uniquindio.mapper.ReporteMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.EstadoResuelto;
import co.edu.uniquindio.model.vo.HistorialEstado;
import co.edu.uniquindio.model.vo.Ubicacion;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ReporteService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteImplement implements ReporteService {

    @Autowired
    ReporteRepo reporteRepo;
    @Autowired
    ReporteMapper reporteMapper;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public void agregarReporte(RegistrarReporteDto reporte) throws ElementoNoEncontradoException {
        Usuario usuario = obtenerPorId(reporte.idUsuario());

        Reporte reporteAux = reporteMapper.toReporte(reporte);
        HistorialEstado historialEstado = new HistorialEstado();
        historialEstado.setUsuario(usuario);
        historialEstado.setEstadoActual(EstadoReporte.PENDIENTE);
        historialEstado.setFecha(LocalDateTime.now());

        List<HistorialEstado> lista = List.of(historialEstado);
        reporteAux.setHistorial(lista);

        Reporte nuevoReporte = reporteRepo.save(reporteAux);
        usuario.getReportes().add(nuevoReporte);

        usuarioRepo.save(usuario);
    }

    @Override
    public void actualizarReporte(EditarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Categoria categoria= obtenerCategoria(reporteDto.categoria().id());
        reporte.setTitulo(reporteDto.titulo());
        reporte.setUbicacion(new Ubicacion(reporteDto.ubicacion().latitud(),reporteDto.ubicacion().altitud(),reporteDto.ubicacion().radio()));
        reporte.setCategoria(categoria);
        reporte.getFotos().clear();
        reporte.getFotos().addAll(reporteDto.fotos());
        //solo guardamos el reporte ya que como van por referencia no hay necesidad de mover el usuario
        reporteRepo.save(reporte);
    }



    @Override
    public void eliminarReporte(EliminarReporteDto reporteDto) throws ElementoNoEncontradoException {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporte.getIdUsuario().toString());
        if (reporteDto.password().equals(usuario.getPassword())) {
            // Elimina el reporte de la lista de reportes del usuario
            usuario.getReportes().removeIf(r -> r.getId().equals(reporte.getId()));

            // Elimina el reporte de la base de datos
            reporteRepo.delete(reporte);

            // Guarda los cambios del usuario
            usuarioRepo.save(usuario);
        } else {
            throw new PermisoDenegadoException("El usuario no existe o la contraseña es incorrecta");
        }
    }



    @Override
    public ReporteDTO buscarReporte(String idReporte) throws ElementoNoEncontradoException {
        Reporte reporte= existeReporte(idReporte);
        return reporteMapper.toReporteDTO(reporte);
    }

    @Override
    public List<ReporteDTO> buscarReportes() throws Exception {
        List<Reporte> reportes = reporteRepo.findAll();
        return reportes.stream().map(reporteMapper::toReporteDTO).collect(Collectors.toList());
    }

    @Override
    public void marcarReporteImportante(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
       // Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        reporte.setNumeroImportancia(reporte.getNumeroImportancia() + 1);
      reporteRepo.save(reporte);
    }

    @Override
    public void quitarReporteImportante(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        // Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        reporte.setNumeroImportancia(reporte.getNumeroImportancia() - 1);
        reporteRepo.save(reporte);
    }

    @Override
    public void marcarReporteFavorito(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getReportes().add(reporte);
        usuarioRepo.save(usuario);
    }

    @Override
    public void quitarReporteFavorito(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getReportes().removeIf(r -> r.getId().equals(reporte.getId()));
        usuarioRepo.save(usuario);
    }

    @Override
    public void marcarReporteResuelto(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        reporte.setEstadoReporte(EstadoResuelto.RESUELTO);
        reporteRepo.save(reporte);
    }

    @Override
    public void quitarReporteResuelto(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        reporte.setEstadoReporte(EstadoResuelto.NO_RESUELTO);
        reporteRepo.save(reporte);
    }

    @Override
    public List<HistorialEstadoDTO> obtenerHistorialEstadosReporte(String idReporte) throws Exception {

        return List.of();
    }

    @Override
    public void gestionarReporte(GestionReporteDto reporte) throws Exception {

    }


    private Usuario obtenerPorId(String id) throws ElementoNoEncontradoException {
        // Buscamos el usuario que se quiere obtener
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }
        Optional<Usuario> optionalCuenta = usuarioRepo.findById(new ObjectId(id));

        // Si no se encontró el usuario, lanzamos una excepción
        if(optionalCuenta.isEmpty()){
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        return optionalCuenta.get();
    }

    private Reporte existeReporte(@NotBlank String id) {
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("No se encontró el reporte con la id "+id);
        }
        Optional<Reporte> reporte= reporteRepo.findById(new ObjectId(id));
        if (reporte.isPresent()) {
            return reporte.get();
        }else {
            throw new ElementoNoEncontradoException(" no existe el reporte con id "+ id);
        }
    }

    private Categoria obtenerCategoria(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("No se encontró la categoria con el id "+id);
        }
        Optional<Categoria>categoria= categoriaRepo.findById(new ObjectId(id));
        if (categoria.isPresent()) {
            return categoria.get();
        }else {
            throw new ElementoNoEncontradoException("No se encontró la categoria con el id "+id);
        }
    }

}
