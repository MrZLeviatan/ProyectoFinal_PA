package co.edu.uniquindio.controller;
import co.edu.uniquindio.dto.reporte.*;
import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping("/registrar")
    public void agregarReporte(@RequestBody RegistrarReporteDto reporte) throws Exception {
        reporteService.agregarReporte(reporte);
    }

    @PutMapping("/actualizar")
    public void actualizarReporte(@RequestBody EditarReporteDto reporte) throws Exception {
        reporteService.actualizarReporte(reporte);
    }

    @DeleteMapping("/eliminar")
    public void eliminarReporte(@RequestBody EliminarReporteDto reporteDto) throws Exception {
        reporteService.eliminarReporte(reporteDto);
    }

    @GetMapping("/{id}")
    public ReporteDTO buscarReporte(@PathVariable String id) throws Exception {
        return reporteService.buscarReporte(id);
    }

    @GetMapping
    public List<ReporteDTO> buscarReportes() throws Exception {
        return reporteService.buscarReportes();
    }

    @PostMapping("/importante/marcar")
    public void marcarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteImportante(dto);
    }

    @PostMapping("/importante/quitar")
    public void quitarImportante(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteImportante(dto);
    }

    @PostMapping("/favorito/marcar")
    public void marcarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteFavorito(dto);
    }

    @PostMapping("/favorito/quitar")
    public void quitarFavorito(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteFavorito(dto);
    }

    @PostMapping("/resuelto/marcar")
    public void marcarResuelto(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.marcarReporteResuelto(dto);
    }

    @PostMapping("/resuelto/quitar")
    public void quitarResuelto(@RequestBody MarcarReporteDto dto) throws Exception {
        reporteService.quitarReporteResuelto(dto);
    }

    @GetMapping("/{id}/historial")
    public List<HistorialEstadoDTO> obtenerHistorial(@PathVariable String id) throws Exception {
        return reporteService.obtenerHistorialEstadosReporte(id);
    }

    @PostMapping("/gestionar")
    public void gestionarReporte(@RequestBody GestionReporteDto dto) throws Exception {
        reporteService.gestionarReporte(dto);
    }
}
