package co.edu.uniquindio.controller;
import co.edu.uniquindio.dto.reporte.*;
import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/registrar")
    public void agregarReporte(@RequestBody RegistrarReporteDto reporte) throws Exception {
        reporteService.agregarReporte(reporte);
    }

    /**
     * Endpoint para actualizar un reporte existente.
     *
     * @param reporte: Objeto con los datos del reporte a actualizar.
     * @throws Exception: Si ocurre un error durante la actualización.
     */
    @PutMapping("/actualizar")
    public void actualizarReporte(@RequestBody EditarReporteDto reporte) throws Exception {
        reporteService.actualizarReporte(reporte);
    }

    /**
     * Endpoint para eliminar un reporte del sistema.
     *
     * @param reporteDto: Objeto con la información necesaria para eliminar el reporte.
     * @throws Exception: Si ocurre un error durante la eliminación.
     */
    @DeleteMapping("/eliminar")
    public void eliminarReporte(@RequestBody EliminarReporteDto reporteDto) throws Exception {
        reporteService.eliminarReporte(reporteDto);
    }

    /**
     * Endpoint para buscar un reporte por su identificador único.
     *
     * @param id: Identificador del reporte.
     * @return Objeto ReporteDTO con la información del reporte encontrado.
     * @throws Exception: Si no se encuentra el reporte o hay un error.
     */
    @GetMapping("/{id}")
    public ReporteDTO buscarReporte(@PathVariable String id) throws Exception {
        return reporteService.buscarReporte(id);
    }

    /**
     * Endpoint para obtener la lista de todos los reportes registrados.
     *
     * @return Lista de objetos ReporteDTO.
     * @throws Exception: Si ocurre un error al obtener los datos.
     */
    @GetMapping
    public List<ReporteDTO> buscarReportes() throws Exception {
        return reporteService.buscarReportes();
    }

    /**
     * Endpoint para marcar un reporte como importante.
     *
     * @param dto: Datos del reporte a marcar como importante.
     * @throws Exception: Si ocurre un error durante el marcado.
     */
    @PostMapping("/importante/marcar")
    public void marcarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteImportante(dto);
    }

    /**
     * Endpoint para quitar la marca de importante a un reporte.
     *
     * @param dto: Datos del reporte al que se le quitará la marca de importante.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @PostMapping("/importante/quitar")
    public void quitarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteImportante(dto);
    }

    /**
     * Endpoint para marcar un reporte como favorito.
     *
     * @param dto: Datos del reporte a marcar como favorito.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @PostMapping("/favorito/marcar")
    public void marcarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteFavorito(dto);
    }

    /**
     * Endpoint para quitar la marca de favorito a un reporte.
     *
     * @param dto: Datos del reporte al que se le quitará la marca de favorito.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
    @PostMapping("/favorito/quitar")
    public void quitarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteFavorito(dto);
    }

    /**
     * Endpoint para marcar un reporte como resuelto.
     *
     * @param dto: Datos del reporte a marcar como resuelto.
     * @throws Exception: Si ocurre un error durante el proceso.
     */
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
    @GetMapping("/{id}/historial")
    public List<HistorialEstadoDTO> obtenerHistorial(@PathVariable String id) throws Exception {
        return reporteService.obtenerHistorialEstadosReporte(id);
    }

    /**
     * Endpoint para gestionar un reporte, lo cual puede implicar asignación o tratamiento específico.
     *
     * @param dto: Datos necesarios para la gestión del reporte.
     * @throws Exception: Si ocurre un error durante la gestión.
     */
    @PostMapping("/gestionar")
    public void gestionarReporte(@RequestBody GestionReporteDto dto) throws Exception {
        reporteService.gestionarReporte(dto);
    }
}
