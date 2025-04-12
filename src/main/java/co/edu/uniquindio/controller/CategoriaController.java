package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {

    // Servicio que contiene la lógica de negocio para operar con categorías.
    CategoriaService categoriaService;

    /**
     * Endpoint para crear una nueva categoría en el sistema.
     * Recibe un DTO con la información de la categoría y la envía al servicio para ser almacenada.
     *
     * @param categoriaDto: Datos de la nueva categoría a crear.
     * @return ResponseEntity sin contenido si la creación es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de creación.
     */
    @PostMapping("/categoria/crear")
    public ResponseEntity<Void> crearCategoria(@RequestBody CrearCategoriaDto categoriaDto) throws Exception {
        categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para editar una categoría existente.
     * Recibe un DTO con los datos actualizados de la categoría y los envía al servicio correspondiente.
     *
     * @param editarCategoriaDto: Datos actualizados de la categoría.
     * @return ResponseEntity sin contenido si la edición es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de edición.
     */
    @PutMapping("/categoria/editar")
    public ResponseEntity<Void> editarCategoria(@RequestBody EditarCategoriaDto editarCategoriaDto) throws Exception {
        categoriaService.editarCategoria(editarCategoriaDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para eliminar una categoría del sistema.
     * Recibe el ID de la categoría a eliminar como parámetro de ruta.
     *
     * @param id: Identificador único de la categoría a eliminar.
     * @return ResponseEntity sin contenido si la eliminación es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de eliminación.
     */
    @DeleteMapping("/categoria/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable String id) throws Exception {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para obtener los detalles de una categoría según su ID.
     *
     * @param id: Identificador único de la categoría.
     * @return ResponseEntity con el DTO de la categoría correspondiente.
     * @throws Exception: Si la categoría no se encuentra o hay un error.
     */
    @GetMapping("/categoria/obtener/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaId(id));
    }

    /**
     * Endpoint para listar todas las categorías registradas en el sistema.
     *
     * @return ResponseEntity con una lista de objetos CategoriaDTO.
     * @throws Exception: Si ocurre un error durante el proceso de obtención.
     */
    @GetMapping("/categoria/listar")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws Exception {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
