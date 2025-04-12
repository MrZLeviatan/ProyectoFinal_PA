package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.reporte.*;

import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.PermisoDenegadoException;
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

    /**
     * Método para agregar un nuevo reporte.
     *
     * @param reporte Objeto DTO con los datos del reporte a registrar.
     * @throws ElementoNoEncontradoException Si no se encuentra el usuario asociado al reporte.
     */
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

    /**
     * Método para actualizar los datos de un reporte existente.
     *
     * @param reporteDto Objeto DTO con los datos actualizados del reporte.
     * @throws Exception Si ocurre algún error durante la actualización.
     */
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

    /**
     * Método para eliminar un reporte.
     *
     * @param reporteDto Objeto DTO con la ID del reporte y la contraseña del usuario.
     * @throws ElementoNoEncontradoException Si el reporte o el usuario no son encontrados.
     * @throws PermisoDenegadoException Si la contraseña proporcionada es incorrecta.
     */
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

    /**
     * Método para buscar un reporte por su ID.
     *
     * @param idReporte ID del reporte a buscar.
     * @return El reporte encontrado convertido a DTO.
     * @throws ElementoNoEncontradoException Si no se encuentra el reporte.
     */
    @Override
    public ReporteDTO buscarReporte(String idReporte) throws ElementoNoEncontradoException {
        Reporte reporte= existeReporte(idReporte);
        return reporteMapper.toReporteDTO(reporte);
    }

    /**
     * Método para obtener todos los reportes.
     *
     * @return Lista de todos los reportes convertidos a DTO.
     * @throws Exception Si ocurre algún error durante la consulta.
     */
    @Override
    public List<ReporteDTO> buscarReportes() throws Exception {
        List<Reporte> reportes = reporteRepo.findAll();
        return reportes.stream().map(reporteMapper::toReporteDTO).collect(Collectors.toList());
    }

    /**
     * Método para marcar un reporte como importante.
     *
     * @param reporteDto Objeto DTO con la ID del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void marcarReporteImportante(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
       // Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        reporte.setNumeroImportancia(reporte.getNumeroImportancia() + 1);
      reporteRepo.save(reporte);
    }

    /**
     * Método para quitar la marca de importante a un reporte.
     *
     * @param reporteDto Objeto DTO con la ID del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void quitarReporteImportante(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        // Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        reporte.setNumeroImportancia(reporte.getNumeroImportancia() - 1);
        reporteRepo.save(reporte);
    }

    /**
     * Método para marcar un reporte como favorito.
     *
     * @param reporteDto Objeto DTO con la ID del reporte y del usuario.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void marcarReporteFavorito(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getReportes().add(reporte);
        usuarioRepo.save(usuario);
    }

    /**
     * Método para quitar un reporte de la lista de favoritos.
     *
     * @param reporteDto Objeto DTO con la ID del reporte y del usuario.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void quitarReporteFavorito(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getReportes().removeIf(r -> r.getId().equals(reporte.getId()));
        usuarioRepo.save(usuario);
    }

    /**
     * Método para marcar un reporte como resuelto.
     *
     * @param reporteDto Objeto DTO con la ID del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void marcarReporteResuelto(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        reporte.setEstadoReporte(EstadoResuelto.RESUELTO);
        reporteRepo.save(reporte);
    }

    /**
     * Método para marcar un reporte como resuelto.
     *
     * @param reporteDto Objeto DTO con la ID del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void quitarReporteResuelto(MarcarReporteDto reporteDto) throws Exception {
        Reporte reporte = existeReporte(reporteDto.idReporte());
        reporte.setEstadoReporte(EstadoResuelto.NO_RESUELTO);
        reporteRepo.save(reporte);
    }

    /**
     * Método para obtener el historial de estados de un reporte.
     *
     * @param idReporte ID del reporte cuyo historial se quiere obtener.
     * @return Lista de historial de estados del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public List<HistorialEstadoDTO> obtenerHistorialEstadosReporte(String idReporte) throws Exception {
        return List.of();
    }

    /**
     * Método para gestionar un reporte.
     *
     * @param reporte Objeto DTO con los datos de gestión del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void gestionarReporte(GestionReporteDto reporte) throws Exception {
    }

    /**
     * Método que obtiene un usuario a partir de su ID.
     *
     * @param id El ID del usuario que se quiere obtener.
     * @return El usuario encontrado.
     * @throws ElementoNoEncontradoException Si el ID no es válido o no se encuentra el usuario.
     */
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

    /**
     * Método que verifica si un reporte existe a partir de su ID.
     *
     * @param id El ID del reporte que se quiere verificar.
     * @return El reporte encontrado.
     * @throws ElementoNoEncontradoException Si el reporte no existe.
     */
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

    /**
     * Método que obtiene una categoría a partir de su ID.
     *
     * @param id El ID de la categoría que se quiere obtener.
     * @return La categoría encontrada.
     * @throws ElementoNoEncontradoException Si el ID no es válido o no se encuentra la categoría.
     */
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
