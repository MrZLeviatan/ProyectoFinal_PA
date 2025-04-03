package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.model.documentos.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses ={ObjectIdMapper.class})
public interface ComentarioMapper {



    // Mapeo de Comentario a ComentarioDTO
    @Mapping(target = "id", source = "id") // Mapea ObjectId a String
    @Mapping(target = "contenido", source = "contenido")
    @Mapping(target = "fechaComentario", source = "fechaComentario")
    @Mapping(target = "idUsuario", source = "idUsuario")
    @Mapping(target = "idReporte", source = "idReporte")
    @Mapping(target = "comentarios", source = "comentarios") // Mapea la lista de comentarios recursivos
    ComentarioDTO toComentarioDTO(Comentario comentario);

    // Mapeo de una lista de Comentarios a una lista de ComentarioDTOs
    List<ComentarioDTO> toComentarioDTOList(List<Comentario> comentarios);
}
