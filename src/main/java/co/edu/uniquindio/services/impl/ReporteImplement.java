package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.constants.MensajesError;
import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.reporte.*;

import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.PermisoDenegadoException;
import co.edu.uniquindio.mapper.ReporteMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoReporte;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.model.vo.HistorialEstado;
import co.edu.uniquindio.model.vo.Ubicacion;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ReporteService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteImplement implements ReporteService {

    private final ReporteRepo reporteRepo;
    private final UsuarioRepo usuarioRepo;
    private final CategoriaRepo categoriaRepo;
    private final ReporteMapper reporteMapper;
    private final PasswordEncoder passwordEncoder;


    /**
     * Método para agregar un nuevo dto.
     * @param dto Objeto DTO con los datos del dto a registrar.
     * @throws ElementoNoEncontradoException Si no se encuentra el usuario asociado al dto.
     */
    @Override
    public ReporteDTO agregarReporte(RegistrarReporteDto dto) throws Exception {
        Usuario usuario = obtenerUsuarioAutenticado();
        if(usuario == null) {
            throw new NullPointerException("Usuario no encontrado");
        }
        if (!Objects.equals(usuario.getId(), new ObjectId(dto.idUsuario()))){
            throw new PermisoDenegadoException("no puedes crear reportes por otros usuarios");
        }
        Reporte reporte = reporteMapper.toReporte(dto);
        agregarHistorial(reporte,EstadoReporte.PENDIENTE,"acaba de ser creado");
        Reporte nuevoReporte = reporteRepo.save(reporte);
        usuario.getReportes().add(nuevoReporte);
        usuarioRepo.save(usuario);
        return reporteMapper.toReporteDTO(reporte);
    }


    /**
     * Método para actualizar los datos de un reporte existente.
     *
     * @param dto Objeto DTO con los datos actualizados del reporte.
     * @return
     * @throws Exception Si ocurre algún error durante la actualización.
     */
    @Override
    public ReporteDTO actualizarReporte(EditarReporteDto dto) throws Exception {
        Usuario usuario = obtenerUsuarioAutenticado();
        Reporte reporte = obtenerReporte(dto.idReporte());
        if (!Objects.equals(usuario.getId(), reporte.getIdUsuario())){
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }
        Categoria categoria = obtenerCategoria(dto.categoria().id());
        reporte.setTitulo(dto.titulo());
        reporte.setUbicacion(new Ubicacion(dto.ubicacion().latitud(),dto.ubicacion().altitud(),dto.ubicacion().radio()));
        reporte.setCategoria(categoria);
        reporte.getFotos().clear();
        reporte.getFotos().addAll(dto.fotos());
        return reporteMapper.toReporteDTO(reporteRepo.save(reporte));
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
        String idUsuario = obtenerIdToken();
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
        if(!reporte.getIdUsuario().toString().equals(idUsuario)){
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }
        Usuario usuario = obtenerPorId(reporte.getIdUsuario().toString());
        if(!passwordEncoder.matches(reporteDto.password(), usuario.getPassword())){
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }
        usuario.getReportes().removeIf(r -> r.getId().equals(reporte.getId()));
        agregarHistorial(reporte,EstadoReporte.ELIMINADO,"fue eliminado por el usuario"+ idUsuario);
        usuarioRepo.save(usuario); // guardamos que uno de los reportes esta eliminado
        reporteRepo.save(reporte); //lo guardamos como eliminado
    }

    /**
     * Método para buscar un reporte por su ID.
     *
     * @param idReporte ID del reporte a buscar.
     * @return El reporte encontrado convertido a DTO.
     * @throws ElementoNoEncontradoException Si no se encuentra el reporte.
     */
    @Override
    public ReporteDTO buscarReporte(String idReporte) throws ElementoNoEncontradoException{
        return reporteMapper.toReporteDTO(obtenerReporte(idReporte));
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getListaReportesFavorito().add(reporte);
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
        Usuario usuario = obtenerPorId(reporteDto.idUsuario());
        usuario.getListaReportesFavorito().removeIf(r -> r.getId().equals(reporte.getId()));
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
        reporte.setSolucionado(EstadoReporte.RESUELTO);
        agregarHistorial(reporte,EstadoReporte.RESUELTO,"");
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
        Reporte reporte = obtenerReporte(reporteDto.idReporte());
        reporte.setSolucionado(EstadoReporte.NO_RESUELTO);
        agregarHistorial(reporte,EstadoReporte.NO_RESUELTO,"");
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
        Reporte reporte= obtenerReporte(idReporte);
        return reporteMapper.historialEstodotoDTO(reporte.getHistorial());
    }

    /**
     * Método para gestionar un reporte.
     *
     * @param dto Objeto DTO con los datos de gestión del reporte.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    @Override
    public void gestionarReporte(GestionReporteDto dto) throws Exception {
        Usuario usuario= obtenerPorId(dto.idUsuario());

        if(usuario.getRol()!= Rol.MODERADOR){
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }

        Reporte reporte = obtenerReporte(dto.idReporte());

        if(dto.estadoActual().equals(EstadoReporte.NO_RESUELTO)||dto.estadoActual().equals(EstadoReporte.RESUELTO)){
            throw new PermisoDenegadoException("eso no se deberia de gestionar en este metodo");
        }
        reporte.setEstadoReporte(dto.estadoActual());
        agregarHistorial(reporte,dto.estadoActual(),"");
        reporteRepo.save(reporte);

    }





    // ============================
    // === MÉTODOS AUXILIARES  ====
    // ============================

    private Usuario obtenerPorId(String id) throws ElementoNoEncontradoException {
        validarObjectId(id, "usuario");
        return usuarioRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException("No se encontró el usuario con id: " + id));
    }
    private Reporte obtenerReporte(@NotBlank String id) {
        validarObjectId(id, "reporte");
        return reporteRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException("No se encontró el reporte con id: " + id));
    }

    private Categoria obtenerCategoria(String id) throws Exception {
        validarObjectId(id, "categoría");
        return categoriaRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException("No se encontró la categoría con id: " + id));
    }

    private Usuario obtenerUsuarioAutenticado() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepo.findById(new ObjectId(user.getUsername()))
                .orElseThrow(() -> new ElementoNoEncontradoException("Usuario autenticado no encontrado"));
    }

    private void validarObjectId(String id, String tipo) {
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("El id de " + tipo + " no es válido: " + id);
        }
    }

    private void agregarHistorial(Reporte reporte, EstadoReporte estado, String motivo) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstadoActual(estado);
        historial.setFecha(LocalDateTime.now());
        historial.setMotivoCambio(motivo);

        if (reporte.getHistorial() == null) {
            reporte.setHistorial(new ArrayList<>());
        }

        reporte.getHistorial().add(historial);
    }

    private String obtenerIdToken() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
