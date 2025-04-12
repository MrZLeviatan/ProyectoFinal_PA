package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;

import java.util.List;

public interface CategoriaService {
    /**
     * Crea una nueva categoría en el sistema.
     * @param categoriaDto Datos de la categoría a crear.
     * @throws Exception Sí ocurre un error durante la creación.
     */
    void crearCategoria(CrearCategoriaDto categoriaDto) throws Exception;

    /**
     * Edita una categoría existente en el sistema.
     * @param editarCategoriaDto Datos actualizados de la categoría.
     * @throws Exception Sí ocurre un error durante la edición.
     */
    void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws Exception;

    /**
     * Elimina una categoría del sistema en función de su identificador único.
     * @param id Identificador único de la categoría.
     * @throws Exception Sí ocurre un error durante la eliminación.
     */
    void eliminarCategoria(String id) throws Exception;

    /**
     * Obtiene una categoría por su identificador único.
     * @param id Identificador único de la categoría.
     * @return Objeto CategoríaDTO con la información de la categoría.
     * @throws Exception Si ocurre un error al obtener la categoría.
     */
    CategoriaDTO obtenerCategoriaId(String id) throws Exception;

    /**
     * Obtiene una lista de todas las categorías disponibles en el sistema.
     * @return Lista de objetos CategoriaDTO.
     * @throws Exception Si ocurre un error al recuperar las categorías.
     */
    List<CategoriaDTO> listarCategorias() throws Exception;
}
