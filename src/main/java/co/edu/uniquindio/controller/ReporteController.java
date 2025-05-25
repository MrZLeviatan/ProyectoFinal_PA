package co.edu.uniquindio.controller;
import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.reporte.*;
        import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.services.ReporteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/reportes")
@RequiredArgsConstructor

// Controlador encargado de gestionar los reportes dentro del sistema.
// Incluye operaciones para registrar, actualizar, eliminar, buscar y marcar reportes con diferentes estados o etiquetas.
public class ReporteController {


    private final ReporteService reporteService;

    /**
     * Endpoint para registrar un nuevo reporte en el sistema.
     *
     * @param reporte: Objeto con los datos del reporte a registrar.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<MensajeDTO<ReporteDTO>> agregarReporte(@RequestBody RegistrarReporteDto reporte) throws Exception {
        ReporteDTO info = reporteService.agregarReporte(reporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    /**
     * Endpoint para actualizar un reporte existente.
     *
     * @param reporte: Objeto con los datos del reporte a actualizar.
     * @throws Exception: Si ocurre un error durante la actualización.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO<ReporteDTO>> actualizarReporte(@RequestBody EditarReporteDto reporte) throws Exception {
        ReporteDTO info = reporteService.actualizarReporte(reporte);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    /**
     * Endpoint para eliminar un reporte del sistema.
     *
     * @param reporteDto: Objeto con la información necesaria para eliminar el reporte.
     * @throws Exception: Si ocurre un error durante la eliminación.
     */
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping
    public ResponseEntity<MensajeDTO<String>> eliminarReporte(@RequestBody EliminarReporteDto reporteDto) throws Exception {
        reporteService.eliminarReporte(reporteDto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte eliminado exitosamente"));
    }

    /**
     * Endpoint para buscar un reporte por su identificador único.
     *
     * @param id: Identificador del reporte.
     * @return Objeto ReporteDTO con la información del reporte encontrado.
     * @throws Exception: Si no se encuentra el reporte o hay un error.
     */
//    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO<ReporteDTO>> buscarReporte(@PathVariable String id) throws Exception {
        ReporteDTO info = reporteService.buscarReporte(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, info));
    }

    /**
     * Endpoint para obtener la lista de todos los reportes registrados.
     *
     * @return Lista de objetos ReporteDTO.
     * @throws Exception: Si ocurre un error al obtener los datos.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public  ResponseEntity<List<ReporteDTO>> buscarReportes() throws Exception {
        return ResponseEntity.ok(reporteService.buscarReportes());
    }

    /**
     * Endpoint para marcar un reporte como importante.
     *
     * @param dto: Datos del reporte a marcar como importante.
     * @throws Exception: Si ocurre un error durante el marcado.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/importante/marcar")
    public ResponseEntity<MensajeDTO<String>> marcarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteImportante(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte marcado Importante exitosamente"));

    }

    /**
     * Endpoint para quitar la marca de importante a un reporte.
     *
     * @param dto: Datos del reporte al que se le quitará la marca de importante.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/importante/quitar")
    public  ResponseEntity<MensajeDTO<String>> quitarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteImportante(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte quitado marca importante exitosamente"));

    }

    /**
     * Endpoint para marcar un reporte como favorito.
     *
     * @param dto: Datos del reporte a marcar como favorito.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/favorito/marcar")
    public  ResponseEntity<MensajeDTO<String>> marcarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteFavorito(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte marca favorito exitosamente"));
    }

    /**
     * Endpoint para quitar la marca de favorito a un reporte.
     *
     * @param dto: Datos del reporte al que se le quitará la marca de favorito.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/favorito/quitar")
    public ResponseEntity<MensajeDTO<String>> quitarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteFavorito(dto);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte quitar marca favorito exitosamente"));

    }

    /**
     * Endpoint para marcar un reporte como resuelto.
     *
     * @param dto: Datos del reporte a marcar como resuelto.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/resuelto/marcar")
    public void marcarResuelto(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteResuelto(dto);
    }

    /**
     * Endpoint para quitar la marca de resuelto a un reporte.
     *
     * @param dto: Datos del reporte al que se le quitará la marca de resuelto.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/resuelto/quitar")
    public void quitarResuelto(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteResuelto(dto);
    }

    /**
     * Endpoint para obtener el historial de estados de un reporte específico.
     *
     * @param id: Identificador del reporte.
     * @return Lista de objetos HistorialEstadoDTO con los cambios de estado del reporte.
     * @throws Exception: Si ocurre un error al recuperar el historial.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}/historial")
    public ResponseEntity<List<HistorialEstadoDTO>> obtenerHistorial(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(reporteService.obtenerHistorialEstadosReporte(id));
    }

    /**
     * Endpoint para gestionar un reporte, lo cual puede implicar asignación o tratamiento específico.
     *
     * @param dto: Datos necesarios para la gestión del reporte.
     * @throws Exception: Si ocurre un error durante la gestión.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/gestionar")
    public void gestionarReporte(@RequestBody GestionReporteDto dto) throws Exception {
        reporteService.gestionarReporte(dto);
    }


    /**
     * Endpoint para obtener reportes dentro de un radio específico de una ubicación.
     *
     * @param ubicacionDTO: Objeto con la ubicación y el radio de búsqueda.
     * @return Lista de reportes encontrados dentro del radio especificado.
     * @throws Exception: Si ocurre un error durante la búsqueda.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/ubicacion")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> obtenerReportesPorUbicacion(@RequestBody UbicacionDTO ubicacionDTO) throws Exception {
        log.info(ubicacionDTO.toString());
        List<ReporteDTO> reportes = reporteService.obtenerReportesUbicacion(ubicacionDTO);
        log.info("Reportes obtenidos por ubicación: {}", reportes.toString());

        return ResponseEntity.ok(new MensajeDTO<>(false, reportes));
    }



}


