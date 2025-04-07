package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.model.documentos.Notificacion;
import org.mapstruct.Mapper;


import java.util.List;
//hola
@Mapper(componentModel = "spring",uses = {ObjectIdMapper.class,})
public interface NotificacionMapper {



    NotificacionDTOM toNotificacionDTO(Notificacion notificacion);

    List<NotificacionDTOM> toNotificacionDTOList(List<Notificacion> notificaciones);
}
