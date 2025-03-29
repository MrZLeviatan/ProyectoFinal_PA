package co.edu.uniquindio.services;

import co.edu.uniquindio.dto.comentario.*;

import java.util.List;

public interface ComentarioInterface {

    /**
     * Agrega un nuevo comentario a un reporte existente.
     * @param comentario Objeto RegistrarComentarioDto con la información del comentario a agregar.
     * @throws Exception Si ocurre un error al agregar el comentario.
     */
    void agregarComentario(RegistrarComentarioDto comentario) throws Exception;

    /**
     * Actualiza un comentario existente en un reporte.
     * @param comentario Objeto EditarComentarioDto con la información actualizada del comentario.
     * @throws Exception Si ocurre un error al actualizar el comentario.
     */
    void actualizarComentario(EditarComentarioDto comentario) throws Exception;

    /**
     * Elimina un comentario de un reporte.
     * @param comentario Objeto EliminarComentarioDto con la información del comentario a eliminar.
     * @throws Exception Si ocurre un error al eliminar el comentario.
     */
    void eliminarComentario(EliminarComentarioDto comentario) throws Exception;

    /**
     * Busca un comentario por su identificador único.
     * @param idComentario ID del comentario a buscar.
     * @return ComentarioDTO con la información del comentario encontrado.
     * @throws Exception Si ocurre un error durante la búsqueda o el comentario no existe.
     */
    ComentarioDTO buscarComentario(String idComentario) throws Exception;

    /**
     * Obtiene la lista de comentarios asociados a un reporte específico.
     * @param idReporte Identificador único del reporte.
     * @return Lista de ComentarioDTO con los comentarios del reporte.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    List<ComentarioDTO> buscarComentariosReporte(String idReporte) throws Exception;

    /**
     * Agrega una respuesta a un comentario existente en un reporte.
     * @param respuestaComentario Objeto que contiene la información de la respuesta.
     * @throws Exception Si ocurre un error durante el proceso.
     */
    void agregarRespuestaComentario(RespuestaComentarioDto respuestaComentario) throws Exception;

    /**
     * Busca las respuestas asociadas a un comentario específico.
     * @param idComentario Identificador único del comentario.
     * @return Lista de respuestas en formato ComentarioDTO.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    List<ComentarioDTO> buscarRespuestaComentario(String idComentario) throws Exception;
}
