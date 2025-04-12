package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.ElementoRepetidoException;
import co.edu.uniquindio.mapper.CategoriaMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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
    public void crearCategoria(CrearCategoriaDto categoriaDto) throws ElementoNoEncontradoException {

        if (categoriaRepo.existsByNombre(categoriaDto.nombre())) {
            throw new ElementoRepetidoException("La categoría con el nombre " + categoriaDto.nombre() + " ya existe");
        }
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
    public void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws ElementoNoEncontradoException {
        Categoria categoria = buscarCategoriaPorId(editarCategoriaDto.id());
        categoria.setNombre(editarCategoriaDto.nombre());
        categoria.setDescripcion(editarCategoriaDto.descripcion());
        if (categoriaRepo.existsByNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("La categoría con el nombre " + categoria.getNombre() + " ya existe");
        }
        categoriaRepo.save(categoria);
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar.
     * @throws ElementoNoEncontradoException Si no se encuentra la categoría con el ID proporcionado.
     */
    @Override
    public void eliminarCategoria(String id) {
        Categoria categoria= buscarCategoriaPorId(id);
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
    public CategoriaDTO obtenerCategoriaId(String id)  throws ElementoNoEncontradoException{
        return categoriaMapper.toCategoriaDTO(buscarCategoriaPorId(id));
    }

    /**
     * Lista todas las categorías y las convierte en DTOs.
     *
     * @return Lista de DTOs con los datos de todas las categorías.
     * @throws Exception Si ocurre algún error durante la obtención de las categorías.
     */
    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaMapper.toCategoriaDTOList(
                categoriaRepo.findAll().stream()
                        .sorted(Comparator.comparing(Categoria::getNombre))
                        .toList()
        );
    }
    /**
     * Busca una categoría por su ID.
     *
     * @param id ID de la categoría a buscar.
     * @return La categoría correspondiente al ID.
     * @throws ElementoNoEncontradoException Si no se encuentra la categoría con el ID proporcionado.
     */
    private Categoria buscarCategoriaPorId(String id) {
        return categoriaRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException("La categoría con id " + id + " no existe"));
    }
}