package co.edu.uniquindio.controller;

import co.edu.uniquindio.dto.MensajeDTO;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.services.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    // Servicio que contiene la lógica de negocio para operar con categorías.
    private final CategoriaService categoriaService;

    /**
     * Endpoint para crear una nueva categoría en el sistema.
     * Recibe un DTO con la información de la categoría y la envía al servicio para ser almacenada.
     *
     * @param categoriaDto: Datos de la nueva categoría a crear.
     * @return ResponseEntity sin contenido si la creación es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de creación.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public  ResponseEntity<MensajeDTO<String>> crearCategoria(@RequestBody CrearCategoriaDto categoriaDto) throws Exception {
        categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria creada correctamente"));
    }

    /**
     * Endpoint para editar una categoría existente.
     * Recibe un DTO con los datos actualizados de la categoría y los envía al servicio correspondiente.
     *
     * @param editarCategoriaDto: Datos actualizados de la categoría.
     * @return ResponseEntity sin contenido si la edición es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de edición.
     */
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping
    public ResponseEntity<MensajeDTO> editarCategoria(@RequestBody EditarCategoriaDto editarCategoriaDto) throws Exception {
        categoriaService.editarCategoria(editarCategoriaDto);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria editada correctamente"));
    }

    /**
     * Endpoint para eliminar una categoría del sistema.
     * Recibe el ID de la categoría a eliminar como parámetro de ruta.
     *
     * @param id: Identificador único de la categoría a eliminar.
     * @return ResponseEntity sin contenido si la eliminación es exitosa.
     * @throws Exception: Si ocurre un error durante el proceso de eliminación.
     */
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> eliminarCategoria(@PathVariable String id) throws Exception {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(new MensajeDTO<>(false,"Categoria eliminada correctamente"));
    }

    /**
     * Endpoint para obtener los detalles de una categoría según su ID.
     *
     * @param id: Identificador único de la categoría.
     * @return ResponseEntity con el DTO de la categoría correspondiente.
     * @throws Exception: Si la categoría no se encuentra o hay un error.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaId(id));
    }
    /**
     * Endpoint para listar todas las categorías registradas en el sistema.
     *
     * @return ResponseEntity con una lista de objetos CategoriaDTO.
     * @throws Exception: Si ocurre un error durante el proceso de obtención.
     */
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws Exception {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
