package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.model.documentos.Notificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Interfaz encargada de definir los métodos para transformar objetos de tipo `Notificacion`
 * y `NotificacionDTOM`. Utiliza MapStruct para realizar el mapeo entre entidades y DTOs.
 */
@Mapper(componentModel = "spring",uses = {ObjectIdMapper.class,})
public interface NotificacionMapper {

    /**
     * Transforma un objeto de tipo `Notificacion` en un objeto de tipo `NotificacionDTOM`.
     *
     * @param notificacion El objeto `Notificacion` a transformar.
     * @return El objeto `NotificacionDTOM` correspondiente.
     */
    @Mapping(target = "CorreoDestinatario", ignore = true) // Se ignora el campo `CorreoDestinatario` en el mapeo
    @Mapping(target = "CorreoRemitente", ignore = true) // Se ignora el campo `CorreoRemitente` en el mapeo
    NotificacionDTOM toNotificacionDTO(Notificacion notificacion);

    /**
     * Transforma una lista de objetos de tipo `Notificacion` en una lista de objetos de tipo `NotificacionDTOM`.
     *
     * @param notificaciones La lista de objetos `Notificacion` a transformar.
     * @return La lista de objetos `NotificacionDTOM` correspondientes.
     */
    List<NotificacionDTOM> toNotificacionDTOList(List<Notificacion> notificaciones);
}

