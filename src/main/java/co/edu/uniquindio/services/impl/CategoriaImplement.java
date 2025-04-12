package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.CategoriaMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para gestionar categorías.
 * Este servicio permite crear, editar, eliminar y listar categorías en el sistema.
 * También incluye la conversión entre entidades y DTOs mediante un mapper.
 */
@Service
@RequiredArgsConstructor
public class CategoriaImplement implements CategoriaService {

    private final CategoriaRepo categoriaRepo;
    private final CategoriaMapper categoriaMapper;

    /**
     * Crea una nueva categoría a partir de un DTO.
     *
     * @param categoriaDto DTO con los datos de la nueva categoría.
     * @throws Exception Si ocurre algún error durante la creación de la categoría.
     */
    @Override
    public void crearCategoria(CrearCategoriaDto categoriaDto) throws Exception {
        Categoria categoria = categoriaMapper.toCategoria(categoriaDto);
        categoriaRepo.save(categoria);
    }

    /**
     * Edita los detalles de una categoría existente.
     *
     * @param editarCategoriaDto DTO con los nuevos detalles para la categoría.
     * @throws Exception Si ocurre algún error durante la edición de la categoría.
     */
    @Override
    public void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws Exception {
        Categoria categoriaModificada = buscarCategoriaId(editarCategoriaDto.id());
        categoriaModificada.setNombre(editarCategoriaDto.nombre());
        categoriaModificada.setDescripcion(editarCategoriaDto.descripcion());
        categoriaRepo.save(categoriaModificada);
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar.
     * @throws ElementoNoEncontradoException Si no se encuentra la categoría con el ID proporcionado.
     */
    @Override
    public void eliminarCategoria(String id) throws ElementoNoEncontradoException {
        Categoria categoria = buscarCategoriaId(id);
        categoriaRepo.delete(categoria);
    }

    /**
     * Obtiene una categoría por su ID y la convierte en un DTO.
     *
     * @param id ID de la categoría a obtener.
     * @return DTO con los datos de la categoría.
     * @throws Exception Si ocurre algún error durante la obtención de la categoría.
     */
    @Override
    public CategoriaDTO obtenerCategoriaId(String id) throws Exception {
        return categoriaMapper.toCategoriaDTO(buscarCategoriaId(id)); // Convierte la entidad Categoria en un DTO
    }

    /**
     * Lista todas las categorías y las convierte en DTOs.
     *
     * @return Lista de DTOs con los datos de todas las categorías.
     * @throws Exception Si ocurre algún error durante la obtención de las categorías.
     */
    @Override
    public List<CategoriaDTO> listarCategorias() throws Exception {
        return categoriaMapper.toCategoriaDTOList(obtenerCategorias()); // Convierte las categorías a DTOs
    }

    /**
     * Obtiene todas las categorías del repositorio.
     *
     * @return Lista de categorías.
     */
    private List<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll(); // Obtiene todas las categorías del repositorio
    }

    /**
     * Busca una categoría por su ID.
     *
     * @param id ID de la categoría a buscar.
     * @return La categoría correspondiente al ID.
     * @throws ElementoNoEncontradoException Si no se encuentra la categoría con el ID proporcionado.
     */
    private Categoria buscarCategoriaId(String id) {
        Optional<Categoria> categoria = categoriaRepo.findById(new ObjectId(id)); // Busca la categoría por ID
        if (categoria.isPresent()) {
            return categoria.get(); // Devuelve la categoría encontrada
        } else {
            throw new ElementoNoEncontradoException("La categoría con el ID " + id + " no existe");
        }
    }
}

