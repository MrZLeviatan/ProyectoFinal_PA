package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.reporte.HistorialEstadoDTO;
import co.edu.uniquindio.dto.moderador.GestionReporteDto;
import co.edu.uniquindio.dto.reporte.*;

import java.util.List;

public interface ReporteService {

    /**
     * Agrega un nuevo reporte al sistema.
     * @param reporte Datos del reporte a registrar.
     * @throws Exception Sí ocurre un error durante el registro.
     */
    void agregarReporte(RegistrarReporteDto reporte) throws Exception;

    /**
     * Actualiza la información de un reporte existente en el sistema.
     * @param reporte Datos actualizados del reporte.
     * @throws Exception Sí ocurre un error durante la actualización.
     */
    void actualizarReporte(EditarReporteDto reporte) throws Exception;

    /**
     * Elimina un reporte del sistema basado en la información proporcionada.
     * @param reporteDto Contiene los datos necesarios para identificar y eliminar el reporte.
     * @throws Exception Sí ocurre un error durante la eliminación del reporte.
     */
    void eliminarReporte(EliminarReporteDto reporteDto) throws Exception;

    /**
     * Busca un reporte en el sistema por su ID.
     * @param idReporte Identificador único del reporte a buscar.
     * @return ReporteDTO con la información del reporte encontrado.
     * @throws Exception Si el reporte no existe o ocurre un error durante la búsqueda.
     */
    ReporteDTO buscarReporte(String idReporte) throws Exception;

    /**
     * Recupera la lista de todos los reportes registrados en el sistema.
     * @return Lista de objetos ReporteDTO con la información de los reportes.
     * @throws Exception Sí ocurre un error durante la recuperación de los reportes.
     */
    List<ReporteDTO> buscarReportes() throws Exception;

    /**
     * Marca un reporte como importante según la votación de un usuario.
     * @param reporte Contiene la información del usuario y el reporte a marcar como importante.
     * @throws Exception Si el usuario ya ha votado por este reporte o si ocurre un error en el proceso.
     */
    void marcarReporteImportante(MarcarReporteDto reporte) throws Exception;

    /**
     * Quita la marca de importante de un reporte previamente votado por un usuario.
     * @param reporte Contiene la información del usuario y el reporte a desmarcar como importante.
     * @throws Exception Si el usuario no ha votado por este reporte o si ocurre un error en el proceso.
     */
    void quitarReporteImportante(MarcarReporteDto reporte) throws Exception;

    /**
     * Marca un reporte como favorito para un usuario específico.
     * @param reporte Contiene la información del usuario y el reporte a marcar como favorito.
     * @throws Exception Si el reporte ya está en la lista de favoritos del usuario o si ocurre un error en el proceso.
     */
    void marcarReporteFavorito(MarcarReporteDto reporte) throws Exception;

    /**
     * Quita un reporte de la lista de favoritos de un usuario.
     * @param reporte Contiene la información del usuario y el reporte a remover de favoritos.
     * @throws Exception Si el reporte no está en la lista de favoritos del usuario o si ocurre un error en el proceso.
     */
    void quitarReporteFavorito(MarcarReporteDto reporte) throws Exception;

    /**
     * Marca un reporte como resuelto.
     * @param reporte Contiene la información del reporte que será marcado como resuelto.
     * @throws Exception Si el reporte no existe o si ocurre un error en el proceso.
     */
    void marcarReporteResuelto(MarcarReporteDto reporte) throws Exception;

    /**
     * Quita la marca de resuelto de un reporte.
     * @param reporte Contiene la información del reporte al que se le removerá la marca de resuelto.
     * @throws Exception Si el reporte no existe o si ocurre un error en el proceso.
     */
    void quitarReporteResuelto(MarcarReporteDto reporte) throws Exception;


    /**
     * Obtiene el historial de estados de un reporte específico.
     * @param idReporte Identificador único del reporte.
     * @return Lista de objetos HistorialEstadoDTO que representan los cambios de estado del reporte.
     * @throws Exception Si el reporte no existe o si ocurre un error en la consulta.
     */
    List<HistorialEstadoDTO> obtenerHistorialEstadosReporte(String idReporte) throws Exception;

    /**
     * Gestiona un reporte permitiendo actualizar su estado. Se agrega un HistorialEstado.
     * @param reporte Objeto GestiónReporteDto con la información necesaria para gestionar el reporte.
     * @throws Exception Sí ocurre un error durante la gestión del reporte.
     */
    void gestionarReporte(GestionReporteDto reporte) throws Exception;

}





