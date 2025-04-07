package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.reporte.*;

import co.edu.uniquindio.repositorios.ReporteRepo;
import co.edu.uniquindio.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReporteImplement implements ReporteService {

    ReporteRepo reporteRepo;

    @Override
    public void agregarReporte(RegistrarReporteDto reporte) throws Exception {
        
    }

    @Override
    public void actualizarReporte(EditarReporteDto reporte) throws Exception {

    }

    @Override
    public void eliminarReporte(EliminarReporteDto reporteDto) throws Exception {

    }

    @Override
    public ReporteDTO buscarReporte(String idReporte) throws Exception {
        return null;
    }

    @Override
    public List<ReporteDTO> buscarReportes() throws Exception {
        return List.of();
    }

    @Override
    public void marcarReporteImportante(MarcarReporteDto reporte) throws Exception {

    }

    @Override
    public void quitarReporteImportante(MarcarReporteDto reporte) throws Exception {

    }

    @Override
    public void marcarReporteFavorito(MarcarReporteDto reporte) throws Exception {

    }

    @Override
    public void quitarReporteFavorito(MarcarReporteDto reporte) throws Exception {

    }

    @Override
    public void marcarReporteResuelto(MarcarReporteResueltoDto reporte) throws Exception {

    }

    @Override
    public void quitarReporteResuelto(MarcarReporteDto reporte) throws Exception {

    }

    @Override
    public List<HistorialEstadoDTO> obtenerHistorialEstadosReporte(String idReporte) throws Exception {
        return List.of();
    }

    @Override
    public void gestionarReporte(GestionReporteDto reporte) throws Exception {

    }
}
