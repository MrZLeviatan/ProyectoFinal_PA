package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTOM;
import co.edu.uniquindio.model.documentos.Notificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
//hola
@Mapper(componentModel = "spring",uses = {ObjectIdMapper.class,})
public interface NotificacionMapper {


    @Mapping(target = "CorreoDestinatario", ignore = true)
    @Mapping(target = "CorreoRemitente", ignore = true)
    NotificacionDTOM toNotificacionDTO(Notificacion notificacion);

    List<NotificacionDTOM> toNotificacionDTOList(List<Notificacion> notificaciones);
}
