package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.usuario.CodigoValidacionDTO;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import org.mapstruct.Mapper;

/**
 * Interfaz encargada de definir los métodos para transformar objetos de tipo `CodigoValidacion`
 * y `CodigoValidacionDTO`. Utiliza MapStruct para realizar el mapeo entre DTOs y entidades de dominio.
 */
@Mapper(componentModel = "spring")
public interface CodigoActivacionMapper {

    /**
     * Transforma un objeto de tipo `CodigoValidacionDTO` en un objeto de tipo `CodigoValidacion`.
     *
     * @param codigoValidacionDTO El DTO con la información del código de validación.
     * @return El objeto `CodigoValidacion` correspondiente.
     */
    CodigoValidacion toCodigoValidacion(CodigoValidacionDTO codigoValidacionDTO);

    /**
     * Transforma un objeto de tipo `CodigoValidacion` en un objeto de tipo `CodigoValidacionDTO`.
     *
     * @param codigoValidacion El objeto `CodigoValidacion` a transformar.
     * @return El objeto `CodigoValidacionDTO` correspondiente.
     */
    CodigoValidacionDTO toCodigoValidacionDTO(CodigoValidacion codigoValidacion);
}
