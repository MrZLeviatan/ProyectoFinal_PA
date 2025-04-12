package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.reporte.UbicacionDTO;
import co.edu.uniquindio.model.vo.Ubicacion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz que define los métodos para mapear entre la entidad `Ubicacion`
 * y su correspondiente DTO (`UbicacionDTO`). Utiliza MapStruct para facilitar
 * la conversión automática de datos.
 */
@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    /**
     * Mapea un objeto `Ubicacion` a un objeto `UbicacionDTO`.
     *
     * @param ubicacion La entidad `Ubicacion` a convertir.
     * @return El objeto `UbicacionDTO` equivalente.
     */
    UbicacionDTO toUbicacionDTO(Ubicacion ubicacion);

    /**
     * Mapea una lista de objetos `Ubicacion` a una lista de objetos `UbicacionDTO`.
     *
     * @param ubicaciones La lista de entidades `Ubicacion` a convertir.
     * @return La lista de objetos `UbicacionDTO` resultantes.
     */
    List<UbicacionDTO> toUbicacionDTOList(List<Ubicacion> ubicaciones);
}

