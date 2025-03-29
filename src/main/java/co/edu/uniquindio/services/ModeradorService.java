package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;

import java.util.List;

public interface ModeradorService {

    /**
     *  Elimina un moderador del sistema en función de su ID y contraseña.
     * @param cuentaDto: Objeto de transferencia de datos que contiene el ID y la contraseña del usuario.
     * @throws Exception: Si ocurre un error durante la eliminación.
     */
    void eliminarModerador(EliminarCuentaDto cuentaDto) throws Exception;

    /**
     * Actualiza la información del moderador en el sistema.
     * @param moderadorAct: Objeto de transferencia de datos que contiene los detalles actualizados del moderador.
     * @throws Exception: Si ocurre un error durante el proceso de actualización.
     */
    void actualizarModerador(EditarModeradorDto moderadorAct) throws Exception;

    /**
     * Obtiene un moderador basado en su identificador único.
     * @param id: Identificador único del moderador.
     * @return Objeto que contiene los detalles del moderador.
     * @throws Exception: Si el usuario no se encuentra oh ocurre un error.
     */
    UsuarioDTO obtenerModeradorId(String id) throws Exception;

    /**
     * Obtiene un moderador basado en su dirección de correo electrónico.
     * @param email: Dirección de correo electrónico del moderador.
     * @return Objeto que contiene los detalles del moderador.
     * @throws Exception: Si el usuario no se encuentra oh ocurre un error.
     */
    UsuarioDTO obtenerModeradorEmail(String email) throws Exception;

    /**
     * Obtiene una lista de usuarios filtrada por nombre y/o ciudad.
     * @param nombre: (Opcional) Nombre del usuario o parte de él.
     * @param ciudad: (Opcional) Ciudad donde reside el usuario.
     * @param pagina: Número de página (comienza desde 0).
     * @param size: Número de usuarios por página.
     * @return Lista de usuarios que coinciden con los criterios de búsqueda.
     * @throws Exception: Si ocurre un error durante la obtención.
     */
    List<UsuarioDTO> listarUsuarios(String nombre,String ciudad, int pagina, int size) throws Exception;

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


