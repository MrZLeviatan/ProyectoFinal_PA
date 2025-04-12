package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.reporte.RegistrarReporteDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.documentos.Reporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Interfaz que define los métodos para mapear objetos entre la entidad `Reporte`
 * y los DTOs correspondientes. Utiliza MapStruct para facilitar la transformación
 * entre las entidades y los objetos de transferencia de datos (DTOs).
 */
@Mapper(componentModel = "spring", uses = {UbicacionMapper.class, CategoriaMapper.class, ComentarioMapper.class, ObjectIdMapper.class})
public interface ReporteMapper {
    /**
     * Mapea un `RegistrarReporteDto` a la entidad `Reporte`, estableciendo valores constantes
     * para algunos campos como `severidad`, `numeroImportancia`, `historial` y `estadoReporte`.
     *
     * @param reporteDTO El DTO que contiene los datos para crear un nuevo reporte.
     * @return El objeto `Reporte` mapeado.
     */
    @Mapping(target = "severidad", constant = "BAJA") // Valor constante para severidad
    @Mapping(target = "id", ignore = true) // El campo id se ignora (no se asigna)
    @Mapping(target = "comentarios", expression = "java(new java.util.ArrayList<>())") // Se inicializa una lista vacía para comentarios
    @Mapping(target = "numeroImportancia", constant = "0") // Valor constante para numeroImportancia
    @Mapping(target = "historial", expression = "java(new java.util.ArrayList<>())") // Se inicializa una lista vacía para historial
    @Mapping(target = "estadoReporte", constant = "NO_RESUELTO") // Valor constante para estadoReporte
    Reporte toReporte(RegistrarReporteDto reporteDTO);

    /**
     * Mapea un objeto `Reporte` a un `ReporteDTO`.
     *
     * @param reporte El objeto `Reporte` a convertir.
     * @return El objeto `ReporteDTO` correspondiente.
     */
    ReporteDTO toReporteDTO(Reporte reporte);

    /**
     * Mapea una lista de objetos `Reporte` a una lista de objetos `ReporteDTO`.
     *
     * @param reportes La lista de objetos `Reporte` a convertir.
     * @return La lista de objetos `ReporteDTO` correspondientes.
     */
    List<ReporteDTO> toReporteDTOList(List<Reporte> reportes);
}
