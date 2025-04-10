package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import co.edu.uniquindio.model.documentos.Notificacion;
import org.mapstruct.Mapper;


import java.util.List;
//hola
@Mapper(componentModel = "spring")
public interface NotificacionMapper {


    NotificacionDTO toNotificacionDTO(Notificacion notificacion);

    List<NotificacionDTO> toNotificacionDTOList(List<Notificacion> notificaciones);
}
