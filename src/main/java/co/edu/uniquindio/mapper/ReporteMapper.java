package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.model.Reporte;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UbicacionMapper.class, CategoriaMapper.class, ComentarioMapper.class, ObjectIdMapper.class})
public interface ReporteMapper {



    ReporteDTO toReporteDTO(Reporte reporte);

    // Mapeo de una lista de Reporte a una lista de ReporteDTO
    List<ReporteDTO> toReporteDTOList(List<Reporte> reportes);
}
