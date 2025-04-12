package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.model.documentos.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Interfaz encargada de definir los métodos para transformar objetos de tipo `Categoria` y `CategoriaDTO`.
 * Utiliza MapStruct para realizar el mapeo entre DTOs y entidades de dominio.
 */
@Mapper(componentModel = "spring", uses ={ObjectIdMapper.class,NotificacionMapper.class})
public interface CategoriaMapper {

    /**
     * Transforma un objeto de tipo `CrearCategoriaDto` en un objeto de tipo `Categoria`.
     * Se ignora el campo `id` ya que se asume que se genera automáticamente en el backend.
     *
     * @param categoriaDto El DTO con la información de la categoría a crear.
     * @return El objeto `Categoria` correspondiente.
     */
    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(CrearCategoriaDto categoriaDto);

    /**
     * Transforma un objeto de tipo `Categoria` en un objeto de tipo `CategoriaDTO`.
     *
     * @param categoria El objeto `Categoria` a transformar.
     * @return El objeto `CategoriaDTO` correspondiente.
     */
    CategoriaDTO toCategoriaDTO(Categoria categoria);

    /**
     * Transforma una lista de objetos `Categoria` en una lista de objetos `CategoriaDTO`.
     *
     * @param categorias La lista de objetos `Categoria` a transformar.
     * @return Una lista de objetos `CategoriaDTO` correspondientes.
     */
    List<CategoriaDTO> toCategoriaDTOList(List<Categoria> categorias);
}
