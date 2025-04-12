package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.reporte.RegistrarReporteDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.documentos.Reporte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring", uses = {UbicacionMapper.class, CategoriaMapper.class, ComentarioMapper.class, ObjectIdMapper.class})
public interface ReporteMapper {


    @Mapping(target = "severidad", constant = "BAJA")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comentarios",expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "numeroImportancia", constant = "0")
    @Mapping(target = "historial",expression = "java(new java.util.ArrayList<>())" )
    @Mapping(target = "estadoReporte",constant = "NO_RESUELTO")
    Reporte toReporte(RegistrarReporteDto reporteDTO);

    ReporteDTO toReporteDTO(Reporte reporte);

    // Mapeo de una lista de Reporte a una lista de ReporteDTO
    List<ReporteDTO> toReporteDTOList(List<Reporte> reportes);
}
