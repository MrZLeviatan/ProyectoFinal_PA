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
    CategoriaService categoriaService;

    @PostMapping("/categoria/crear")
    public ResponseEntity<Void> crearCategoria(@RequestBody CrearCategoriaDto categoriaDto) throws Exception {
        categoriaService.crearCategoria(categoriaDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/categoria/editar")
    public ResponseEntity<Void> editarCategoria(@RequestBody EditarCategoriaDto editarCategoriaDto) throws Exception {
        categoriaService.editarCategoria(editarCategoriaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/categoria/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable String id) throws Exception {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/obtener/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaId(id));
    }

    @GetMapping("/categoria/listar")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws Exception {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}
