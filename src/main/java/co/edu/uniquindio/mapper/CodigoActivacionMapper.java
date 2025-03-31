package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.usuario.CodigoValidacionDTO;
import co.edu.uniquindio.model.CodigoValidacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodigoActivacionMapper {
    // Mapeo de CodigoValidacionDTO a CodigoValidacion
    CodigoValidacion toCodigoValidacion(CodigoValidacionDTO codigoValidacionDTO);

    // Mapeo de CodigoValidacion a CodigoValidacionDTO
    CodigoValidacionDTO toCodigoValidacionDTO(CodigoValidacion codigoValidacion);
}
