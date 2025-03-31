package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import co.edu.uniquindio.model.Notificacion;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {


    NotificacionDTO toNotificacionDTO(Notificacion notificacion);

    List<NotificacionDTO> toNotificacionDTOList(List<Notificacion> notificaciones);
}
