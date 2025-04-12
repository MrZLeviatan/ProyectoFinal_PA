package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.dto.comentario.RegistrarComentarioDto;
import co.edu.uniquindio.model.documentos.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz encargada de definir los métodos para transformar objetos de tipo `Comentario`
 * y `ComentarioDTO`. Utiliza MapStruct para realizar el mapeo entre DTOs y entidades de dominio.
 */
@Mapper(componentModel = "spring", uses ={ObjectIdMapper.class})
public interface ComentarioMapper {

    /**
     * Transforma un objeto de tipo `Comentario` en un objeto de tipo `ComentarioDTO`.
     *
     * @param comentario El objeto `Comentario` a transformar.
     * @return El objeto `ComentarioDTO` correspondiente.
     */
    @Mapping(target = "id", source = "id") // Mapea ObjectId a String
    @Mapping(target = "contenido", source = "contenido")
    @Mapping(target = "fechaComentario", source = "fechaComentario")
    @Mapping(target = "idUsuario", source = "idUsuario")
    @Mapping(target = "idReporte", source = "idReporte")
    @Mapping(target = "comentarios", source = "comentarios") // Mapea la lista de comentarios recursivos
    ComentarioDTO toComentarioDTO(Comentario comentario);

    /**
     * Transforma una lista de objetos de tipo `Comentario` en una lista de objetos de tipo `ComentarioDTO`.
     *
     * @param comentarios La lista de objetos `Comentario` a transformar.
     * @return La lista de objetos `ComentarioDTO` correspondientes.
     */
    List<ComentarioDTO> toComentarioDTOList(List<Comentario> comentarios);

    /**
     * Transforma un objeto de tipo `RegistrarComentarioDto` en un objeto de tipo `Comentario`.
     *
     * @param comentarioDTO El DTO con la información del comentario a registrar.
     * @return El objeto `Comentario` correspondiente.
     */
    @Mapping(target = "id", ignore = true) // Ignora el campo `id` ya que es generado automáticamente
    @Mapping(target = "idComentario", ignore = true) // Ignora el campo `idComentario`
    @Mapping(target = "comentarios", expression = "java(new java.util.ArrayList<>())") // Inicializa la lista vacía
    @Mapping(target = "fechaComentario", ignore = true) // Ignora el campo `fechaComentario`, será asignado al registrar
    Comentario toComentario(RegistrarComentarioDto comentarioDTO);

}
