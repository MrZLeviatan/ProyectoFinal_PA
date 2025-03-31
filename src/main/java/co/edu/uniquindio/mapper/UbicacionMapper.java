package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.reporte.UbicacionDTO;
import co.edu.uniquindio.model.Ubicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    UbicacionDTO toUbicacionDTO(Ubicacion ubicacion);

    List<UbicacionDTO> toUbicacionDTOList(List<Ubicacion> ubicaciones);
}
