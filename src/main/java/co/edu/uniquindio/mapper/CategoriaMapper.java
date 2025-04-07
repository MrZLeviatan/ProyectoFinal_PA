package co.edu.uniquindio.mapper;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.model.documentos.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses ={ObjectIdMapper.class,NotificacionMapper.class})
public interface CategoriaMapper {
    //transformar crearCategoriaDTO en Categoria
    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(CrearCategoriaDto categoriaDto);
    // Mapeo de Categoria a CategoriaDTO
    CategoriaDTO toCategoriaDTO(Categoria categoria);

    // Mapeo de una lista de Categoria a una lista de CategoriaDTO
    List<CategoriaDTO> toCategoriaDTOList(List<Categoria> categorias);


}